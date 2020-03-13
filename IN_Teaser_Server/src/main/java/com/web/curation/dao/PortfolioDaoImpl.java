package com.web.curation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.mappers.PortfolioMapper;
import com.web.curation.model.Portfolio;
import com.web.curation.model.Project;
import com.web.curation.model.ProjectDetail;
import com.web.curation.model.ProjectLike;
import com.web.curation.model.UserLink;

@Repository
public class PortfolioDaoImpl {

	@Autowired
	private SqlSession sqlSession;

	public Portfolio portfolioInfo(Portfolio portfolio) {
		return sqlSession.getMapper(PortfolioMapper.class).portfolioInfo(portfolio);
	}

	public void updateonoff(Portfolio portfolio) {
		sqlSession.getMapper(PortfolioMapper.class).updateonoff(portfolio);

	}

	public List<UserLink> selectUserlink(UserLink userlink) {
		return sqlSession.getMapper(PortfolioMapper.class).selectUserlink(userlink);
	}

	public void insertUserlink(UserLink userlink) {
		sqlSession.getMapper(PortfolioMapper.class).insertUserlink(userlink);

	}

	public void updateUserlink(UserLink userlink) {
		sqlSession.getMapper(PortfolioMapper.class).updateUserlink(userlink);

	}

	public void deleteUserlink(UserLink userlink) {
		sqlSession.getMapper(PortfolioMapper.class).deleteUserlink(userlink);

	}

	public void insertProject1(Project project) {
		sqlSession.getMapper(PortfolioMapper.class).insertProject1(project);

	}

	public void insertProject2(Project project) {
		sqlSession.getMapper(PortfolioMapper.class).insertProject2(project);

	}

	public void updateProject(Project project) {
		sqlSession.getMapper(PortfolioMapper.class).updateProject(project);

	}

	public void deleteProject(Project project) {
		sqlSession.getMapper(PortfolioMapper.class).deleteProject(project);

	}

	public ProjectDetail detailProject(Project project) {
		return sqlSession.getMapper(PortfolioMapper.class).detailProject(project);

	}

	public List<Project> projectList(Project project) {
		return sqlSession.getMapper(PortfolioMapper.class).projectList(project);
	}

	public void projectLike(ProjectLike projectLike) {
		sqlSession.getMapper(PortfolioMapper.class).projectLike(projectLike);

	}

	public void projectLikeCancel(ProjectLike projectLike) {
		sqlSession.getMapper(PortfolioMapper.class).projectLikeCancel(projectLike);

	}

//	public List<Project> projectSearch(Project project, List<String> technical_stack) {
//		List<Project> projectReult = sqlSession.getMapper(PortfolioMapper.class).projectSearch(project,
//				technical_stack);
//		return projectReult;
//
//	}

	public List<Project> projectDevSettingList(int user_seq) {
		List<Project> projectReult = sqlSession.getMapper(PortfolioMapper.class).projectDevSettingList(user_seq);
		return projectReult;
	}
	
	public List<ProjectDetail> projectSearch(Project project) {
		List<ProjectDetail> searchResult = sqlSession.getMapper(PortfolioMapper.class).projectSearch(project);
		return searchResult;
	}

	public boolean isme(ProjectLike projectLike) {
		return sqlSession.getMapper(PortfolioMapper.class).isme(projectLike);
	}

	public ProjectLike loginUserLike(ProjectLike usercheck) {
		return sqlSession.getMapper(PortfolioMapper.class).loginUserLike(usercheck);
	}

	public void projectLikeAdd(Project project) {
		sqlSession.getMapper(PortfolioMapper.class).projectLikeAdd(project);
	}

	public void projectLikeSubtract(Project project) {
		sqlSession.getMapper(PortfolioMapper.class).projectLikeSubtract(project);
		
	}

	public String getuserProfileRoute(int userSeq) {
		return sqlSession.getMapper(PortfolioMapper.class).getuserProfileRoute(userSeq);
	}

}
