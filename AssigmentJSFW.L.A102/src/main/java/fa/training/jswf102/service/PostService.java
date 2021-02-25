package fa.training.jswf102.service;

import fa.training.jswf102.dto.PostDTO;
import fa.training.jswf102.entities.Post;

import java.util.Map;

public interface PostService extends BaseService<PostDTO,Integer> {
    public Map<String,Object> search(int pageNum,int pageSize, String search,byte status);
}
