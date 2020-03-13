package com.web.curation.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.web.curation.model.Portfolio;
import com.web.curation.model.Project;
import com.web.curation.model.ProjectDetail;
import com.web.curation.model.ProjectLike;
import com.web.curation.model.UserLink;

@Mapper
public interface PortfolioMapper {

	public Portfolio portfolioInfo(Portfolio portfolio);

	public void updateonoff(Portfolio portfolio);
	
	public List<UserLink> selectUserlink(UserLink userlink);

	public void insertUserlink(UserLink userlink);

	public void updateUserlink(UserLink userlink);

	public void deleteUserlink(UserLink userlink);

	public void insertProject1(Project project);

	public void insertProject2(Project project);

	public void updateProject(Project project);

	public void deleteProject(Project project);

	public ProjectDetail detailProject(Project project);

	public List<Project> projectList(Project project);

	public void projectLike(ProjectLike projectLike);

	public void projectLikeCancel(ProjectLike projectLike);

//	public List<Project> projectSearch(Project project, List<String> technical_stack);

	public List<Project> projectDevSettingList(int user_seq);

	public List<ProjectDetail> projectSearch(Project project);

	public boolean isme(ProjectLike projectLike);

	public ProjectLike loginUserLike(ProjectLike usercheck);

	public void projectLikeAdd(Project project);

	public void projectLikeSubtract(Project project);

	public String getuserProfileRoute(int userSeq);


	
}
