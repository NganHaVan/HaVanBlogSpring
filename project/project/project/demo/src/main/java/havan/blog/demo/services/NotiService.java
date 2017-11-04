package havan.blog.demo.services;

public interface NotiService {
	//Provide method for adding error and info msges which is going to use on the view later
	void addInfoMessage(String msg);
	void AddErrorMessage(String msg);
}
