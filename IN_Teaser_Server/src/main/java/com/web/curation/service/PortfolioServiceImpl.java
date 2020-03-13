package com.web.curation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.dao.NoticeDaoImpl;
import com.web.curation.dao.PortfolioDaoImpl;
import com.web.curation.model.Communication;
import com.web.curation.model.Portfolio;
import com.web.curation.model.Project;
import com.web.curation.model.ProjectDetail;
import com.web.curation.model.ProjectLike;
import com.web.curation.model.UserLink;
@Transactional
@Service
public class PortfolioServiceImpl implements IPortfolioService {

	@Autowired
	private PortfolioDaoImpl portfoliodao;

	@Autowired
	private NoticeDaoImpl noticedao;

	@Override
	public Portfolio portfolioInfo(Portfolio portfolio) {
		return portfoliodao.portfolioInfo(portfolio);
	}

	@Override
	public void updateonoff(Portfolio portfolio) {
		portfoliodao.updateonoff(portfolio);

	}

	@Override
	public List<UserLink> selectUserlink(UserLink userlink) {
		List<UserLink> linkResult = portfoliodao.selectUserlink(userlink);
		return linkResult;
	}

	@Override
	public void insertUserlink(UserLink userlink) {
		portfoliodao.insertUserlink(userlink);

	}

	@Override
	public void updateUserlink(UserLink userlink) {
		portfoliodao.updateUserlink(userlink);

	}

	@Override
	public void deleteUserlink(UserLink userlink) {
		portfoliodao.deleteUserlink(userlink);

	}

	@Override
	public void insertProject1(Project project) {
		portfoliodao.insertProject1(project);

	}

	@Override
	public void insertProject2(Project project) {
		portfoliodao.insertProject2(project);

	}

	@Override
	public void updateProject(Project project) {
		portfoliodao.updateProject(project);

	}

	@Override
	public void deleteProject(Project project) {
		portfoliodao.deleteProject(project);
	}

	@Override
	public ProjectDetail detailProject(Project project) {
		return portfoliodao.detailProject(project);

	}

	@Override
	public List<Project> projectList(Project project) {
		List<Project> projectList = portfoliodao.projectList(project);
		return projectList;
	}

	@Override
	public void projectLike(ProjectLike projectLike) {
		portfoliodao.projectLike(projectLike);

		if (portfoliodao.isme(projectLike))
			return;
		int user_auth = noticedao.getuserauth(projectLike.getUser_seq());
		String temp = "";
		if (user_auth == 2)
			temp = "채용담당자<b>" + projectLike.getUser_nickname() + "</b>님이 회원님의 프로젝트에 좋아요를 눌렀습니다.";
		else
			temp = "<b>" + projectLike.getUser_nickname() + "</b>님이 회원님의 프로젝트에 좋아요를 눌렀습니다.";

		Communication comm = new Communication(projectLike.getProject_seq(), projectLike.getUser_nickname(), user_auth,
				temp);
		noticedao.pushnoticebyproject(comm);

	}

	@Override
	public void projectLikeCancel(ProjectLike projectLike) {
		portfoliodao.projectLikeCancel(projectLike);

	}

//	@Override
//	public List<Project> projectSearch(Project project, List<String>technical_stack) {
//		 List<Project> projectResult = portfoliodao.projectSearch(project,technical_stack);
//		return projectResult;
//		
//	}

	@Override
	public List<Project> projectDevSettingList(int user_seq) {
		List<Project> projectDevSettingList = portfoliodao.projectDevSettingList(user_seq);
		return projectDevSettingList;
	}

	@Override
	public List<ProjectDetail> projectSearch(Project project) {
		List<ProjectDetail> searchResult = portfoliodao.projectSearch(project);
		return searchResult;
	}

	@Override
	public ProjectLike loginUserLike(ProjectLike usercheck) {
		return portfoliodao.loginUserLike(usercheck);
	}

	@Override
	public void projectLikeAdd(Project project) {
		portfoliodao.projectLikeAdd(project);
		
	}

	@Override
	public void projectLikeSubtract(Project project) {
		portfoliodao.projectLikeSubtract(project);
		
	}

	@Override
	public String getuserProfileRoute(int userSeq) {
		return portfoliodao.getuserProfileRoute(userSeq);
	}

}
