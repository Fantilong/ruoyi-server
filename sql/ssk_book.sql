-- ----------------------------
-- 图书管理模块 - 菜单、权限、字典初始化脚本
-- 执行前请确保已执行 ssk_book_category.sql 及 ry_20260320.sql
-- 菜单ID从2006开始（2000-2005已分配给图书分类）
-- 字典ID从100开始（1-10已分配给系统字典）
-- ----------------------------

-- ----------------------------
-- 菜单及权限
-- ----------------------------

-- 二级菜单：图书管理（挂载在图书管理目录2000下）
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2006, '图书管理', 2000, 2, 'book', 'ssk/book/index', '', '', 1, 0, 'C', '0', '0', 'ssk:book:list', 'list', 'admin', sysdate(), '', null, '图书管理菜单');

-- 按钮权限：图书查询
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2007, '图书查询', 2006, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:book:query', '#', 'admin', sysdate(), '', null, '');

-- 按钮权限：图书新增
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2008, '图书新增', 2006, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:book:add', '#', 'admin', sysdate(), '', null, '');

-- 按钮权限：图书修改
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2009, '图书修改', 2006, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:book:edit', '#', 'admin', sysdate(), '', null, '');

-- 按钮权限：图书删除
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, route_name, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values (2010, '图书删除', 2006, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'ssk:book:remove', '#', 'admin', sysdate(), '', null, '');

-- ----------------------------
-- 字典类型：书架号
-- ----------------------------
insert into sys_dict_type (dict_id, dict_name, dict_type, status, create_by, create_time, update_by, update_time, remark)
values (100, '书架号', 'book_shelfs', '0', 'admin', sysdate(), '', null, '图书书架号列表');

-- ----------------------------
-- 字典数据：书架号（示例数据，可通过字典管理页面继续添加）
-- ----------------------------
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
values (100, 1, 'B001', 'B001', 'book_shelfs', '', 'primary', 'Y', '0', 'admin', sysdate(), '', null, '1号书架');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
values (101, 2, 'B002', 'B002', 'book_shelfs', '', 'success', 'N', '0', 'admin', sysdate(), '', null, '2号书架');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
values (102, 3, 'B003', 'B003', 'book_shelfs', '', 'info', 'N', '0', 'admin', sysdate(), '', null, '3号书架');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
values (103, 4, 'B004', 'B004', 'book_shelfs', '', 'warning', 'N', '0', 'admin', sysdate(), '', null, '4号书架');
insert into sys_dict_data (dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, update_by, update_time, remark)
values (104, 5, 'B005', 'B005', 'book_shelfs', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '5号书架');
