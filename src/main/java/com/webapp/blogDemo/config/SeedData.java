package com.webapp.blogDemo.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.webapp.blogDemo.models.Account;
import com.webapp.blogDemo.models.Authority;
import com.webapp.blogDemo.models.Post;
import com.webapp.blogDemo.repositories.AuthorityRepository;
import com.webapp.blogDemo.services.AccountService;
import com.webapp.blogDemo.services.FileService;
import com.webapp.blogDemo.services.PostService;

@Component
public class SeedData implements CommandLineRunner{

	@Autowired
	private FileService fileService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;
    
	@Override
	public void run(String... args) throws Exception {
		List<Post> post = postService.getAll();
		
		fileService.init();
		
		if(post.size() == 0) {
			
			Authority user = new Authority();
			user.setName("ROLE_USER");
			authorityRepository.save(user);
			
			Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);
            
            Account account1 = Account
            		.builder()
            		.firstName("first_user")
            		.lastName("user_last")
            		.email("user.user@gmail.com")
            		.password("user")
            		.build();
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthority(authorities1);
            
            Account account2 = Account
                    .builder()
                    .firstName("admin_first")
                    .lastName("admin_last")
                    .email("admin.admin@domain.com")
                    .password("admin")
                    .build();

            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            account2.setAuthority(authorities2);
            
            accountService.save(account1);
            accountService.save(account2);
            
            Post post1 = Post
                    .builder()
                    .title("What is Lorem Ipsum?")
                    .body("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                    .account(account1)
                    .imageFilePath(null)
                    .build();

            Post post2 = Post
                    .builder()
                    .title("Something else Ipsum")
                    .body("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Magna eget est lorem ipsum dolor sit amet consectetur adipiscing. Tempus quam pellentesque nec nam aliquam sem et tortor. Pellentesque sit amet porttitor eget. Sed augue lacus viverra vitae congue eu consequat. Ultrices vitae auctor eu augue. Mattis rhoncus urna neque viverra. Consectetur lorem donec massa sapien faucibus et molestie ac feugiat. Sociis natoque penatibus et magnis dis parturient montes nascetur. Cursus turpis massa tincidunt dui ut ornare lectus. Odio pellentesque diam volutpat commodo sed egestas egestas fringilla. Id cursus metus aliquam eleifend mi. Nibh nisl condimentum id venenatis a condimentum.")
                    .account(account2)
                    .imageFilePath(null)
                    .build();
			
			postService.save(post1);
			postService.save(post2);
		}
		
	}
	
	

}
