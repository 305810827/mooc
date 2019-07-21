//package cn.gzcc.demo.Service;
//
//
//import cn.gzcc.demo.model.entity.User;
//import cn.gzcc.demo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class DatabaseUserDetailsService implements UserDetailsService{
//
//@Autowired
//private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//
////      User user =  userRspository.findFirstByName(name);
//        User user =  userRepository.findByName(name);
//
//      if(user==null) {
//      throw new UsernameNotFoundException(user.getName());
//      }
//      ArrayList<SimpleGrantedAuthority> authorities =new ArrayList<>();
//      SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
//      authorities.add(authority);
//      UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),authorities);
//
//        return userDetails;
//
//    }
//}
