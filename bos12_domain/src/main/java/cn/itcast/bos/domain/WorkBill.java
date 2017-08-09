package cn.itcast.bos.domain;

import java.sql.Timestamp;

/**
 * 工单表实体类
 */

public class WorkBill implements java.io.Serializable {

	// Fields

	private String id;//工单主键id
	private NoticeBill noticeBill;//业务通知单表
	private Staff staff;//取派员
	private String type;//工单类型    新 追  改 销
	private String pickstate;//取件状态  已通知 待取件 已取件
	private Timestamp buildtime;//生成时间
	private Integer attachbilltimes;//追单次数
	private String remark;//备注
	
	/**
	 * 新
	 */
	public static final String TYPE_1 = "1";
	/**
	 * 追
	 */
	public static final String TYPE_2 = "2";
	/**
	 * 改
	 */
	public static final String TYPE_3 = "3";
	/**
	 * 销
	 */
	public static final String TYPE_4 = "4";

	/**
	 * 已通知  已发短信通知取派员
	 */
	public static final String PICKSTATE_1 = "1";

	/**
	 * 待取件
	 */
	public static final String PICKSTATE_2 = "2";
	
	/**
	 * 已取件
	 */
	public static final String PICKSTATE_3 = "3";

	

	// Constructors

	/** default constructor */
	public WorkBill() {
	}

	/** minimal constructor */
	public WorkBill(String id, Timestamp buildtime) {
		this.id = id;
		this.buildtime = buildtime;
	}

	/** full constructor */
	public WorkBill(String id, NoticeBill noticeBill, Staff staff, String type,
			String pickstate, Timestamp buildtime, Integer attachbilltimes,
			String remark) {
		this.id = id;
		this.noticeBill = noticeBill;
		this.staff = staff;
		this.type = type;
		this.pickstate = pickstate;
		this.buildtime = buildtime;
		this.attachbilltimes = attachbilltimes;
		this.remark = remark;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public NoticeBill getNoticeBill() {
		return this.noticeBill;
	}

	public void setNoticeBill(NoticeBill noticeBill) {
		this.noticeBill = noticeBill;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPickstate() {
		return this.pickstate;
	}

	public void setPickstate(String pickstate) {
		this.pickstate = pickstate;
	}

	public Timestamp getBuildtime() {
		return this.buildtime;
	}

	public void setBuildtime(Timestamp buildtime) {
		this.buildtime = buildtime;
	}

	public Integer getAttachbilltimes() {
		return this.attachbilltimes;
	}

	public void setAttachbilltimes(Integer attachbilltimes) {
		this.attachbilltimes = attachbilltimes;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}