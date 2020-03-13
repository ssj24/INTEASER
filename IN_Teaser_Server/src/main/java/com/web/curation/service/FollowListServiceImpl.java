package com.web.curation.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.curation.dao.FollowListDaoImpl;
import com.web.curation.model.Communication;
import com.web.curation.model.FollowList;
import com.web.curation.model.Notice;
import com.web.curation.model.User;
import com.web.curation.model.UserProfile;
@Transactional
@Service
public class FollowListServiceImpl implements IFollowListService {
	@Autowired
	private FollowListDaoImpl followdao;

	@Override
	public int followingcnt(User user) {
		return followdao.followingcnt(user);
	}

	@Override
	public int followercnt(User user) {
		return followdao.followercnt(user);
	}

	@Override
	public List<FollowList> following(User user) {
		return followdao.following(user);
	}

	@Override
	public List<FollowList> follower(User user) {
		return followdao.follower(user);
	}

	@Override
	public int requestcnt(User user) {
		return followdao.requestcnt(user);
	}

	@Override
	public List<FollowList> requestfollower(User user) {
		return followdao.requestfollower(user);
	}

	@Override
	public int noticecnt(User user) {
		return followdao.noticecnt(user);
	}

	@Override
	public List<Notice> noticelist(User user) {
		return followdao.noticelist(user);
	}

	@Override
	public void newCheck1(User user) {
		followdao.newCheck1(user);
	}

	@Override
	public void newCheck2(User user) {
		followdao.newCheck2(user);
	}

	@Override
	public int canFollow1(Communication comm) {
		return followdao.canFollow1(comm);
	}

	@Override
	public String canFollow2(Communication comm) {
		return followdao.canFollow2(comm);
	}

	@Override
	public void requestfollowing1(Communication comm) {
		followdao.requestfollowing1(comm);
	}

	@Override
	public void requestfollowing2(Communication comm) {
		followdao.requestfollowing2(comm);
	}

	@Override
	public void requestfollowing3(Communication comm) {
		followdao.requestfollowing3(comm);
	}

	@Override
	public void requestfollowing4(Communication comm) {
		followdao.requestfollowing4(comm);
	}

	@Override
	public void answer1(FollowList follow) {
		followdao.answer1(follow);
	}

	@Override
	public void answer2(FollowList follow) {
		followdao.answer2(follow);
	}

	@Override
	public void deleteReject(User user) {
		followdao.deleteReject(user);
	}

	@Override
	public int allrequestcnt(User user) {
		return followdao.allrequestcnt(user);
	}

	@Override
	public int allnoticecnt(User user) {
		return followdao.allnoticecnt(user);
	}

	@Override
	public List<Notice> noticecenterlist(User user) {
		return followdao.noticecenterlist(user);
	}

	@Override
	public void checknewrequest1(User user) {
		followdao.checknewrequest1(user);
	}

	@Override
	public void checknewrequest2(User user) {
		followdao.checknewrequest2(user);
	}

	@Override
	public void checknewnotice(User user) {
		followdao.checknewnotice(user);
	}

	@Override
	public int checknotice(User user) {
		return followdao.checknotice(user);
	}

	@Override
	public void answer3(Communication comm) {
		followdao.answer3(comm);
	}

	@Override
	public int getauth(String nickname) {
		return followdao.getauth(nickname);
	}

	@Override
	public void deletenotification(Notice notice) {
		followdao.deletenotification(notice);
	}

	@Override
	public void requestunfollowing1(Communication comm) {
		followdao.requestunfollowing1(comm);
	}

	@Override
	public void requestunfollowing2(Communication comm) {
		followdao.requestunfollowing2(comm);
	}

	@Override
	public List<UserProfile> random(int user_seq) {
		String interest = followdao.getinterest(user_seq);
		System.out.println("TESTETSETESTSETSETSESDTSEST+ "+interest);
		if(interest == null)
			return null;
		
		UserProfile user =new UserProfile(user_seq, interest);

		List<UserProfile> random = followdao.getrandom(user);

		boolean[] randomidx = new boolean[random.size()];
		
		Set<Integer> random5 = new HashSet<Integer>();
		List<UserProfile> recommend = new ArrayList<UserProfile>();
		
		Random rand = new Random();
		while (!isEmpty(randomidx) && random5.size() < 5) {
			int val = rand.nextInt(random.size());
			if (random5.add(val)) {
				recommend.add(random.get(val));
				randomidx[val]=true;
			}
		}
		return recommend;
	}

	private boolean isEmpty(boolean[] random) {
		for (int i = 0; i < random.length; i++) {
			if(!random[i])
				return false;
		}
		return true;
	}

}
