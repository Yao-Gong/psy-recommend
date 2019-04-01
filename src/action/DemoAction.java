package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DemoAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7177924785872739182L;

	public String load()
	{
		ActionContext.getContext().getSession().put("uid", null);
		ActionContext.getContext().getSession().put("user", null);
		ActionContext.getContext().getSession().put("emotion", null);
		ActionContext.getContext().getSession().put("psychology", null);
		ActionContext.getContext().getSession().put("recommendation", null);
		
		return "success";
	}
}
