package com.web.curation.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.curation.model.Portfolio;
import com.web.curation.model.Project;
import com.web.curation.model.ProjectDetail;
import com.web.curation.model.ProjectLike;
import com.web.curation.model.UserLink;

public interface IPortfolioService {

	Portfolio portfolioInfo(Portfolio portfolio);
	
	void updateonoff(Portfolio portfolio);

	List<UserLink> selectUserlink(UserLink userlink);
	
	void insertUserlink(UserLink userlink);

	void updateUserlink(UserLink userlink);

	void deleteUserlink(UserLink userlink);

	void insertProject1(Project project);

	void insertProject2(Project project);

	void updateProject(Project project);

	void deleteProject(Project project);

	ProjectDetail detailProject(Project project);

	List<Project> projectList(Project project);

	void projectLike(ProjectLike projectLike);

	void projectLikeCancel(ProjectLike projectLike);

//	List<Project> projectSearch(Project project,List<String> technical_stack);

	List<Project> projectDevSettingList(int user_seq);

	List<ProjectDetail> projectSearch(Project project);

	ProjectLike loginUserLike(ProjectLike usercheck);

	void projectLikeAdd(Project project);

	void projectLikeSubtract(Project project);

	String getuserProfileRoute(int userSeq);

	

}
