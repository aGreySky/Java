package per.agreysky.sell.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.agreysky.sell.VO.ProductInfoVO;
import per.agreysky.sell.VO.ProductVO;
import per.agreysky.sell.VO.ResultVO;
import per.agreysky.sell.dataobject.ProductCategory;
import per.agreysky.sell.dataobject.ProductInfo;
import per.agreysky.sell.service.CategoryService;
import per.agreysky.sell.service.ProductService;
import per.agreysky.sell.utils.ResultVOUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //1.查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2.查询类目（一次性查询）
        //传统方法
//        List<Integer> categoryTypeList = new ArrayList<>();
//        for (ProductInfo productInfo:productInfoList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        //精简方法（java8,lambda）
        List<Integer> categoryTypeList=productInfoList.stream().map(e ->e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList=categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
        List<ProductVO> productVOList=new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList){
            ProductVO productVO =new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList=new ArrayList<>();
            for (ProductInfo productInfo: productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO=new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}


