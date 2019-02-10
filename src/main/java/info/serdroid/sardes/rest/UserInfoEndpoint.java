package info.serdroid.sardes.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import info.serdroid.sardes.model.UserInfo;
import info.serdroid.sardes.service.UserInfoService;

@ApplicationScoped
@Path("user")
public class UserInfoEndpoint {

	@Inject
	private UserInfoService userInfoService;

	@GET
	@Path("/{id}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public UserInfo userInfo(@PathParam("id") String userId) {
		return userInfoService.userInfo(userId);
    }

}
