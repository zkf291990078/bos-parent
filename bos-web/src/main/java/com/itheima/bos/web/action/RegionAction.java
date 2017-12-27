package com.itheima.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Region;
import com.itheima.bos.service.RegionService;
import com.itheima.bos.utils.PinYin4jUtils;
import com.itheima.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

	private File regionFile;
	private List<Region> regionList = new ArrayList<>();
	@Autowired
	private RegionService regionService;
	private String q;

	public String importXls() throws Exception {
		// TODO Auto-generated method stub
		// 包装一个Excel文件对象
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
		// 读取文件中第一个Sheet标签页
		HSSFSheet hssfSheet = workbook.getSheetAt(0);
		// 遍历标签页中所有的行
		for (Row row : hssfSheet) {
			if (row.getRowNum() == 0) {
				continue;
			}
			String id = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			Region region = new Region(id, province, city, district, postcode, null, null, null);
			province = province.substring(0, province.length() - 1);
			city = city.substring(0, city.length() - 1);
			district = district.substring(0, district.length() - 1);

			String info = province + city + district;
			String[] headByString = PinYin4jUtils.getHeadByString(info);
			String shortcode = StringUtils.join(headByString);
			// 城市编码---->>shijiazhuang
			String citycode = PinYin4jUtils.hanziToPinyin(city, "");

			region.setShortcode(shortcode);
			region.setCitycode(citycode);
			regionList.add(region);
		}
		regionService.saveBatch(regionList);

		return NONE;
	}

	public String list() throws Exception {
		// TODO Auto-generated method stub
		List<Region> regions = null;
		if (StringUtils.isNoneBlank(q)) {
			regions = regionService.findDataByq(q);
		} else {
			regions = regionService.findAll();
		}
		java2Json(regions, new String[] { "subareas" });
		return NONE;
	}

	public String pageQuery() throws Exception {
		// TODO Auto-generated method stub
		regionService.queryPage(pageBean);
		java2Json(pageBean, new String[] { "currentPage", "detachedCriteria", "pageSize", "subareas" });
		return NONE;
	}

	public String add() throws Exception {
		// TODO Auto-generated method stub
		String province = model.getProvince();
		String city = model.getCity();
		String district = model.getDistrict();
		province = province.substring(0, province.length() - 1);
		city = city.substring(0, city.length() - 1);
		district = district.substring(0, district.length() - 1);

		String info = province + city + district;
		String[] headByString = PinYin4jUtils.getHeadByString(info);
		String shortcode = StringUtils.join(headByString);
		// 城市编码---->>shijiazhuang
		String citycode = PinYin4jUtils.hanziToPinyin(city, "");

		model.setShortcode(shortcode);
		model.setCitycode(citycode);
		regionService.save(model);
		return LIST;
	}

	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}

	public void setQ(String q) {
		this.q = q;
	}

}
