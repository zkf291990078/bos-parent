package com.itheima.bos.web.action;

import java.io.OutputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.SubareaService;
import com.itheima.bos.utils.FileUtils;
import com.itheima.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {

	@Autowired
	private SubareaService subareaService;

	public String add() throws Exception {
		// TODO Auto-generated method stub
		// if (StringUtils.isNotBlank(model.getId())) {
		// subareaService.edit(model);
		// } else {

		subareaService.save(model);
		// }

		return LIST;
	}

	public String pageQuery() throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria dCriteria = pageBean.getDetachedCriteria();
		if (StringUtils.isNotBlank(model.getAddresskey())) {
			dCriteria.add(Restrictions.like("addresskey", "%" + model.getAddresskey() + "%"));
		}
		if (model.getRegion() != null) {
			String province = model.getRegion().getProvince();
			String city = model.getRegion().getCity();
			String district = model.getRegion().getDistrict();
			dCriteria.createAlias("region", "r");
			if (StringUtils.isNotBlank(province)) {
				dCriteria.add(Restrictions.like("r.province", "%" + province + "%"));
			}
			if (StringUtils.isNotBlank(city)) {
				dCriteria.add(Restrictions.like("r.city", "%" + city + "%"));
			}
			if (StringUtils.isNotBlank(province)) {
				dCriteria.add(Restrictions.like("r.district", "%" + district + "%"));
			}
		}

		subareaService.queryPage(pageBean);
		java2Json(pageBean, new String[] { "currentPage", "detachedCriteria", "pageSize", "subareas", "decidedzone" });
		return NONE;
	}

	public String exportXls() throws Exception {
		List<Subarea> subareas = subareaService.findAll();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("分区数据");
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("开始编号");
		headRow.createCell(2).setCellValue("结束编号");
		headRow.createCell(3).setCellValue("位置信息");
		headRow.createCell(4).setCellValue("省市区");

		for (Subarea subarea : subareas) {
			HSSFRow rHssfRow = sheet.createRow(sheet.getLastRowNum() + 1);
			rHssfRow.createCell(0).setCellValue(subarea.getId());
			rHssfRow.createCell(1).setCellValue(subarea.getStartnum());
			rHssfRow.createCell(2).setCellValue(subarea.getEndnum());
			rHssfRow.createCell(3).setCellValue(subarea.getPosition());
			rHssfRow.createCell(4).setCellValue(subarea.getRegion().getName());
		}
		String fileName = "分区数据.xls";
		String contentType = ServletActionContext.getServletContext().getMimeType(fileName);
		ServletActionContext.getResponse().setContentType(contentType);
		OutputStream outputStream = ServletActionContext.getResponse().getOutputStream();

		// 获取客户端浏览器类型
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		fileName = FileUtils.encodeDownloadFilename(fileName, agent);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + fileName);
		workbook.write(outputStream);
		return NONE;
	}
	
	public String listajax() throws Exception {
		// TODO Auto-generated method stub
		List<Subarea> subareas=subareaService.findSubareaNoDecidedzone();
		java2Json(subareas, new String[]{"decidedzone", "region"});
		return NONE;
	}
}
