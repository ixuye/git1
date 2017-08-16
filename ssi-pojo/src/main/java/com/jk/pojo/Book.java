package com.jk.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 
 * <pre>项目名称：ssi    
 * 类名称：Book    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月14日 下午3:20:09    
 * 修改人：徐叶    
 * 修改时间：2017年7月14日 下午3:20:09    
 * 修改备注：       
 * @version </pre>
 */
public class Book implements Serializable{
	private static final long serialVersionUID = 2319016408529501101L;
	
	@Id
	@Column(name="bookID")
	private Integer bookID;
	
	@Column(name="bookName")
	private String bookName;
	
	
	
	
	
	
	
	
	
	public String getBookName() {
		return bookName;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Integer getBookID() {
		return bookID;
	}

	public void setBookID(Integer bookID) {
		this.bookID = bookID;
	}

	@Override
	public String toString() {
		return "Book [bookID=" + bookID + ", bookName=" + bookName + "]";
	}
	
	
}
