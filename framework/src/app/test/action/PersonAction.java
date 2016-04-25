package app.test.action;

import org.nxf.frame.action.Action;
import org.nxf.frame.service.PersonService;

public class PersonAction extends Action{

	private static final long serialVersionUID = 1L;
	
	
	public PersonAction() {
		// TODO Auto-generated constructor stub
		this.setService(new PersonService("tbl_person","app.test.bean.Person"));
	}
};
