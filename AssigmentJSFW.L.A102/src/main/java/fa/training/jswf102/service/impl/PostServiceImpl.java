package fa.training.jswf102.service.impl;

import fa.training.jswf102.converter.PostConverter;
import fa.training.jswf102.dto.PostDTO;
import fa.training.jswf102.entities.*;
import fa.training.jswf102.exception.NotFoundException;
import fa.training.jswf102.repository.*;
import fa.training.jswf102.service.PostService;
import fa.training.jswf102.utils.Constant;
import javafx.geometry.Pos;
import org.apache.log4j.Logger;
import org.apache.tomcat.jni.Poll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private PostRepository postRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired
    private
    PostTagRepository postTagRepository;
    @Autowired private TagRepository tagRepository;
    @Autowired private UserRepository userRepository;
    @Override
    public Map<String, Object> search(int pageNum, int pageSize, String search,byte status) {
        logger.info(pageNum + "-" + pageSize);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Post> pagePost = postRepository.searchPost(pageable, search,status);

        List<PostDTO> list = new ArrayList<>();
        pagePost.toList().forEach(post -> {
            PostDTO postDTO = postConverter.convertToDTO(post);
            postDTO.setCommentCount(commentRepository.countByPostId(post.getId()));
            list.add(postDTO);
        });
        Map<String, Object> map = new HashMap<>();
        map.put("postList", list);
        map.put("currentPage", pageNum);
        map.put("totalPage", pagePost.getTotalPages());
        map.put("totalElements", pagePost.getTotalElements());
        return map;
    }

    @Override
    public List<PostDTO> findAll() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOS = new ArrayList<>();
        posts.forEach(post -> postDTOS.add(postConverter.convertToDTO(post)));
        return postDTOS;
    }

    @Override
    public PostDTO findById(Integer id) throws NotFoundException {
        Post post = postRepository.getPostById(id);
        if(post == null){
            throw  new NotFoundException("Post Not Found");
        }
        return postConverter.convertToDTO(post);
    }

    @Override
    public PostDTO save(PostDTO postDTO) throws NotFoundException {
        if(postDTO.getTitle().trim().equalsIgnoreCase("")){
            throw new IllegalArgumentException("Title Is Require");
        }
        if("".equalsIgnoreCase(postDTO.getContent().trim())){
            throw new IllegalArgumentException("Content is require") ;
        }
        Post post = postConverter.convertToEntity(postDTO);
        Set<String> tags = new HashSet<>(Arrays.asList(post.getTag().split(",")));
        List<PostTag> postTagList = new ArrayList<>();
        Optional<User> user = userRepository.getByEmail(postDTO.getAuthor().getEmail());
        if(user.isPresent()){
            post.setAuthor(user.get());
        }else{
            throw new NotFoundException("Author Not Found. Access is reject");
        }
        tags.forEach(tag ->{
            Tag tag1 = tagRepository.getByName(tag);
            if(tag1 != null){
                tag1.setFrequency(tag1.getFrequency()+1);
                PostTagId postTagId = new PostTagId(post,tag1);
                PostTag postTag = new PostTag(postTagId);
                postTagList.add(postTag);
            }else{
                tag1 = new Tag();
                tag1.setFrequency(1);
                tag1.setName(tag);
                PostTagId postTagId = new PostTagId(post,tagRepository.save(tag1));
                PostTag postTag = new PostTag(postTagId);
                postTagList.add(postTag);
            }
        });
        logger.info(tags);
        post.setPostTags(postTagList);
        logger.info(postTagList);
        post.setCreateTime(new Date());
        post.setModifiedTime(new Date());
        return postConverter.convertToDTO(postRepository.save(post));
    }

    @Override
    public PostDTO update(PostDTO postDTO, Integer id) throws NotFoundException {
        Optional<Post> postUpdate =postRepository.findById(id);
        if (!postUpdate.isPresent()) {
            throw new NotFoundException("Post Not Found. Cannot Update");
        }
        if(postDTO.getTitle().trim().equalsIgnoreCase("")){
            throw new IllegalArgumentException("Title Is Require");
        }
        if("".equalsIgnoreCase(postDTO.getContent().trim())){
            throw new IllegalArgumentException("Content is require") ;
        }
        Optional<User> user = userRepository.getByEmail(postDTO.getAuthor().getEmail());
        if(user.isPresent()){
            postUpdate.get().setAuthor(user.get());
        }else{
            throw new NotFoundException("Author Not Found. Access is reject");
        }
        postUpdate.get().setCreateTime(postUpdate.get().getCreateTime());
        postUpdate.get().setModifiedTime(new Date());
        postUpdate.get().setStatus(postDTO.getStatus());
        postUpdate.get().setTitle(postDTO.getTitle());
        postUpdate.get().setTag(postDTO.getTag());
        postUpdate.get().setContent(postDTO.getContent());
        return postConverter.convertToDTO(postRepository.save(postUpdate.get()));
    }

    @Transactional
    @Override
    public boolean delete(Integer id) throws NotFoundException {
        Post post = postConverter.convertToEntity(findById(id));
        if (post == null) {
            throw new NotFoundException("Post Not Found. Cannot Delete");
        }
        try {
            post.setStatus(Constant.DELETE_STATUS);
            postRepository.save(post);
            return true;
        } catch (Exception e) {
            logger.error(e);
        }
        return false;
    }
}
