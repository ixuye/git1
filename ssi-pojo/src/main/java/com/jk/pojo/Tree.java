package com.jk.pojo;

import java.util.List;

public class Tree {

	private Integer id;
	
	
	private Integer treeParentId;
	
	private String url;
	
	private String text;

	
	private List<Tree> nodes;

	
	
	


	public Integer getTreeParentId() {
		return treeParentId;
	}


	public void setTreeParentId(Integer treeParentId) {
		this.treeParentId = treeParentId;
	}


	


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public List<Tree> getNodes() {
		return nodes;
	}


	public void setNodes(List<Tree> nodes) {
		this.nodes = nodes;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	@Override
	public String toString() {
		return "Tree [id=" + id + ", treeParentId=" + treeParentId + ", url=" + url + ", text=" + text + ", nodes="
				+ nodes + "]";
	}
	
	
}
