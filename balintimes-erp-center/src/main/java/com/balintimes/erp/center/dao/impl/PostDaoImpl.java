package com.balintimes.erp.center.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.center.dao.PostDao;
import com.balintimes.erp.center.mappers.PostMapper;
import com.balintimes.erp.center.model.Post;
import com.balintimes.erp.center.tuples.TuplePage;

@Repository("PostDao")
public class PostDaoImpl implements PostDao {

	@Resource
	private PostMapper postMapper;

	public List<Post> GetPostList() {
		// TODO Auto-generated method stub
		return this.postMapper.GetPostList();
	}

	public TuplePage<List<Post>, Integer> GetPostListByPage(Post post,
			int page, int pageSize) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>(4);
		params.put("parentuid", post.getParentuid());
		params.put("uid", post.getUid());
		params.put("name", post.getName());
		params.put("organizationuid", post.getOrganizationuid());
		List<Post> list = postMapper.GetPostListByPage(params);
		int total = list.size();
		return new TuplePage<List<Post>, Integer>(list, total);
	}

	public void UpdatePost(Post post) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>(9);		
		params.put("uid", post.getUid());
		params.put("name", post.getName());
		params.put("parentuid", post.getParentuid());
		params.put("organizationuid", post.getOrganizationuid());
		params.put("comment", post.getComment());
		params.put("createby", post.getCreateby());
		params.put("createtime", post.getCreatetime());
		params.put("editby", post.getEditby());
		params.put("edittime", post.getEdittime());
		
		this.postMapper.UpdatePost(params);
	}
	
	public void DetetePost(String uid) {
		// TODO Auto-generated method stub
		//this.postMapper.DeleteUserPost(uid);
		this.postMapper.DeletePost(uid);
	}
	
	public boolean CreatePost(Post post) {
		// TODO Auto-generated method stub
		try {
			this.postMapper.CreatePost(post);
			return true;
		} catch (Exception e) {
			// TODO: handle com.balintimes.erp.center.exception
			return false;
		}		
	}

	
	public List<Post> GetPostSet(String PostName) {
		// TODO Auto-generated method stub		
		return this.postMapper.GetPostSet(PostName);
	}

	
	public Post GetOnePost(String uid) {
		// TODO Auto-generated method stub
		return this.postMapper.GetOnePost(uid);
	}

	
	public List<Post> GetPostByEmployee(String useruid) {
		return this.postMapper.GetPostByEmployee(useruid);		
	}

	
	public List<Post> GetPostParent(String postuid, String organizationuid) {
		Map<String,Object> params=new HashMap<String, Object>(4);
		params.put("uid", postuid);
		params.put("organizationuid", organizationuid);
		
		List<Post> posts=this.postMapper.GetPostParent(params);
		return posts;
	}

	
	public List<Post> GetOneUserPosts(String useruid) {
		List<Post> postList=this.postMapper.GetOneUserPosts(useruid);
		return postList;
	}

}
