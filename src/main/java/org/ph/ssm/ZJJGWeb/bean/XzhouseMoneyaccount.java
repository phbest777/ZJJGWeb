package org.ph.ssm.ZJJGWeb.bean;


public class XzhouseMoneyaccount {

  private String id;
  private String xybh;
  private String recordno;
  private String ckr;
  private Double price;
  private String operator;
  private String cklb;
  private String cklbname;
  private String flag;
  private String flaganme;
  private String cklsh;
  private String code;
  private String msg;
  private String datadate;
  private String datatime;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getXybh() {
    return xybh;
  }

  public void setXybh(String xybh) {
    this.xybh = xybh;
  }


  public String getRecordno() {
    return recordno;
  }

  public void setRecordno(String recordno) {
    this.recordno = recordno;
  }


  public String getCkr() {
    return ckr;
  }

  public void setCkr(String ckr) {
    this.ckr = ckr;
  }


  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }


  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }


  public String getCklb() {
    return cklb;
  }

  public void setCklb(String cklb) {
    this.cklb = cklb;
  }


  public String getCklbname() {
    return cklbname;
  }

  public void setCklbname(String cklb) {
    switch(cklb){
      case "1":
        this.cklbname="用户存款";
        break;
      case "2":
        this.cklbname="商业贷款";
        break;
      case "3":
        this.cklbname="公积金贷款";
        break;
      case "4":
        this.cklbname="调账补款";
        break;
      default:
        this.cklbname="没有匹配代码";
        break;
    }
  }


  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }


  public String getFlaganme() {
    return flaganme;
  }

  public void setFlaganme(String flag) {
    switch(flag){
      case "0":
        this.flaganme="银行";
        break;
      case "1":
        this.flaganme="开发商";
        break;
      case "2":
        this.flaganme="专用POS";
        break;
      default:
        this.flaganme="没有匹配代码";
        break;
    }
  }


  public String getCklsh() {
    return cklsh;
  }

  public void setCklsh(String cklsh) {
    this.cklsh = cklsh;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }


  public String getDatadate() {
    return datadate;
  }

  public void setDatadate(String datadate) {
    this.datadate = datadate;
  }


  public String getDatatime() {
    return datatime;
  }

  public void setDatatime(String datatime) {
    this.datatime = datatime;
  }

}
