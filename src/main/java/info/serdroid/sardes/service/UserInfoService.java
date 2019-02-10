package info.serdroid.sardes.service;

import javax.enterprise.context.ApplicationScoped;

import info.serdroid.sardes.model.UserInfo;

@ApplicationScoped
public class UserInfoService {
	
	public UserInfo userInfo(String userId) {
		return createDummyUser();
	}

	private UserInfo createDummyUser() {
    	UserInfo user = new UserInfo();
    	user.setUserid("123");
    	user.setName("ali");
    	user.setLastname("veli");
    	user.setUsertype(0);
    	return user;
    }

}
