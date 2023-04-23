package com.szeport.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * T_STATISTICAL_GOODS_MONTH_SUM
 * </p>
 *
 * @author jiangmb
 * @since 2023-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("macro_data_dev.T_STATISTICAL_GOODS_MONTH_SUM")
@ApiModel(value = "TStatisticalGoodsMonthSum对象", description = "T_STATISTICAL_GOODS_MONTH_SUM")
public class TStatisticalGoodsMonthSum implements Serializable {


    @ApiModelProperty(value = "顺序号")
    @TableId(value = "SEQ_NO", type = IdType.AUTO)
    private String seqNo;

    @ApiModelProperty(value = "企业统一编号")
    @TableField("CODE_SCC")
    private String codeScc;

    @ApiModelProperty(value = "企业代码海关编码 企业注册地")
    @TableField("TRADE_CODE")
    private String tradeCode;

    @ApiModelProperty(value = "进出口 ")
    @TableField("IE_FLAG")
    private String ieFlag;

    @ApiModelProperty(value = "商品编码（前四位）")
    @TableField("CODE_TS")
    private String codeTs;

    @ApiModelProperty(value = "贸易市场 进口取原产国（地区）出口取最终目的国（地区）")
    @TableField("TRADE_MARKET")
    private String tradeMarket;

    @ApiModelProperty(value = "贸易方式")
    @TableField("TRADE_MODE")
    private String tradeMode;

    @ApiModelProperty(value = "贸易额度(RMB)")
    @TableField("AMOUNT_RMB")
    private Float amountRmb;

    @ApiModelProperty(value = "贸易额度(USD)")
    @TableField("AMOUNT_USD")
    private Float amountUsd;

    @ApiModelProperty(value = "统计日期")
    @TableField("STAT_DATE")
    private LocalDate statDate;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "回执创建日期")
    @TableField("CRE_DATE")
    private LocalDate creDate;

    @ApiModelProperty(value = "区属")
    private String district;


}
