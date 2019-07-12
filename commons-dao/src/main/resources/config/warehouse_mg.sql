
/*
仓库管理系统：原理管理，产品管理，产品系列管理，订单管理
 */

DROP TABLE IF EXISTS tb_user;
CREATE TABLE tb_user
(
  user_id bigint primary key auto_increment,
  username VARCHAR(200) comment '用户名',
  password VARCHAR (30) comment '密码',
  privilege INT comment '权限：1管理员',
  gmt_create datetime,
  gmt_update datetime,
  yn INT
) comment='用户表' ENGINE=InnoDB DEFAULT CHARSET='utf8';


DROP TABLE IF EXISTS tb_material;
CREATE TABLE tb_material
(
  material_id bigint primary key auto_increment,
  material_name VARCHAR(200) comment '材料名称',
  material_unit VARCHAR (30) comment '材料单位',
  material_unit_price FLOAT comment '材料单价',
  material_num FLOAT comment '材料总量',
  gmt_create datetime,
  gmt_update datetime,
  yn INT
) comment='原材料' ENGINE=InnoDB DEFAULT CHARSET='utf8';


DROP TABLE IF EXISTS tb_material_in;
CREATE TABLE tb_material_in
(
  material_in_id bigint primary key auto_increment,
  material_id bigint comment '材料id',
  material_name VARCHAR(200) comment '材料名称',
  material_in_num FLOAT comment '材料入库数量',
  in_unit VARCHAR(20) comment '入库单位',
  gmt_create datetime,
  gmt_update datetime,
  yn INT,
  INDEX material_id_index(material_id)
) comment='原材料入库表' ENGINE=InnoDB DEFAULT CHARSET='utf8';


DROP TABLE IF EXISTS tb_product;
CREATE TABLE tb_product(
  product_id bigint primary key auto_increment,
  product_name VARCHAR (200) comment'产品名称',
  product_model VARCHAR (200) comment'产品型号',
  product_mould_model VARCHAR (200) comment'模具型号',
  product_num bigint comment'产品库存数量',
  gmt_create datetime,
  gmt_update datetime,
  yn INT
) comment='产品' ENGINE=InnoDB DEFAULT CHARSET='utf8';


DROP TABLE IF EXISTS tb_product_material;
CREATE TABLE tb_product_material(
  product_material_id bigint primary key auto_increment,
  product_id bigint comment'产品ID',
  product_num bigint comment'产品入库数量',
  gmt_create datetime,
  gmt_update datetime,
  yn INT,
  INDEX product_id_index(product_id)
) comment='产品和原材料关系（产品入库表）' ENGINE=InnoDB DEFAULT CHARSET='utf8';


DROP TABLE IF EXISTS tb_material_details;
CREATE TABLE tb_material_details(
  material_details_id bigint primary key auto_increment,
  product_id bigint comment'产品ID',
  material_id bigint comment'原材料ID',
  product_material_id bigint comment'产品入库ID',
  material_use_num FLOAT comment '材料使用量',
  gmt_create datetime,
  gmt_update datetime,
  yn INT,
  INDEX product_id_index(product_id),
  INDEX material_id_index(material_id)
) comment='产品入库使用材料明细表' ENGINE=InnoDB DEFAULT CHARSET='utf8';



DROP TABLE IF EXISTS tb_product_material_num;
CREATE TABLE tb_product_material_num(
  product_material_num_id bigint primary key auto_increment,
  product_id bigint comment'产品ID',
  material_id bigint comment'原材料ID',
  material_num FLOAT comment'原材料使用数量',
  gmt_create datetime,
  gmt_update datetime,
  yn INT,
  INDEX product_id_index(product_id),
  INDEX material_id_index(material_id)
) comment='产品和原材料价格关系（一个产品需要用的原材料数量）' ENGINE=InnoDB DEFAULT CHARSET='utf8';


DROP TABLE IF EXISTS tb_product_series_name;
CREATE TABLE tb_product_series_name(
  product_series_name_id bigint primary key auto_increment,
  product_series_name VARCHAR (100) comment '系列名称',
  gmt_create datetime,
  gmt_update datetime,
  yn INT
) comment='产品系列名称表' ENGINE=InnoDB DEFAULT CHARSET='utf8';


DROP TABLE IF EXISTS tb_product_series;
CREATE TABLE tb_product_series(
  product_series_id bigint primary key auto_increment,
  product_series_name_id bigint comment '系列名称ID',
  product_id bigint comment'产品ID',
  gmt_create datetime,
  gmt_update datetime,
  yn INT,
  INDEX product_series_name_id_index(product_series_name_id)
) comment='产品系列' ENGINE=InnoDB DEFAULT CHARSET='utf8';


DROP TABLE IF EXISTS tb_order;
CREATE TABLE tb_order(
  order_id bigint primary key auto_increment,
  order_num VARCHAR (200) comment '订单编号',
  order_date VARCHAR (30) comment '订单日期',
  buyer_name VARCHAR (200) comment '购买方名称',
  buyer_address VARCHAR (200) comment '购买方地址',
  buyer_contact VARCHAR (200) comment '购买方联系人',
  buyer_phone VARCHAR (200) comment '购买方电话',
  buyer_mail VARCHAR (200) comment '购买方邮箱',
  gmt_create datetime,
  gmt_update datetime,
  yn INT
) comment='订单' ENGINE=InnoDB DEFAULT CHARSET='utf8';


DROP TABLE IF EXISTS tb_order_product;
CREATE TABLE tb_order_product(
  order_product_id bigint primary key auto_increment,
  order_id bigint comment '订单ID',
  product_id bigint comment'产品ID',
  product_num bigint comment'产品数量',
  delivery_date VARCHAR (30) comment '交货日期',
  gmt_create datetime,
  gmt_update datetime,
  INDEX order_id_index(order_id),
  INDEX product_id_index(product_id),
  yn INT
) comment='订单产品' ENGINE=InnoDB DEFAULT CHARSET='utf8';



DROP TABLE IF EXISTS tb_order_product_material;
CREATE TABLE tb_order_product_material(
  order_product_material_id bigint primary key auto_increment,
  order_product_id bigint comment'订单的产品ID',
  material_name VARCHAR(200) comment '材料名称',
  material_unit VARCHAR (30) comment '材料单位',
  material_unit_price FLOAT comment '材料单价',
  material_num FLOAT comment '材料数量',
  gmt_create datetime,
  gmt_update datetime,
  yn INT,
  INDEX order_product_id_index(order_product_id)
) comment='订单产品原料关系' ENGINE=InnoDB DEFAULT CHARSET='utf8';