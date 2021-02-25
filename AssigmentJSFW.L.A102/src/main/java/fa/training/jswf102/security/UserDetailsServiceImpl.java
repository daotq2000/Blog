package fa.training.jswf102.security;


import fa.training.jswf102.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.List;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService  {
	@Autowired
	UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
private Logger logger = Logger.getLogger(this.getClass());
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		fa.training.jswf102.entities.User user =  userRepository.checkExistUsername(username);
		logger.info(user);
		if(user != null){
				List<GrantedAuthority> grantedAuthorities = AuthorityUtils
		                	.commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());

				return new User(user.getEmail(), user.getPassword(), grantedAuthorities);

		}
		System.out.println("found");
		throw new UsernameNotFoundException("Username: " + username + " not found");
	}
}