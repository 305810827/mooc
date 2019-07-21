
<!--注册 art template 数字格式化显示过滤器 doubleFormat -->
template.defaults.imports.doubleFormat = function (value, precision, separator) {
  var parts;
  // 判断是否为数字
  if (!isNaN(parseFloat(value)) && isFinite(value)) {
    // 把类似 .5, 5. 之类的数据转化成0.5, 5, 为数据精度处理做准, 至于为什么
    // 不在判断中直接写 if (!isNaN(num = parseFloat(num)) && isFinite(num))
    // 是因为parseFloat有一个奇怪的精度问题, 比如 parseFloat(12312312.1234567119)
    // 的值变成了 12312312.123456713
    value = Number(value);
    // 处理小数点位数
    value = (typeof precision !== 'undefined' ? value.toFixed(precision) : value).toString();
    // 分离数字的小数部分和整数部分
    parts = value.split('.');
    // 整数部分加[separator]分隔, 借用一个著名的正则表达式
    parts[0] = parts[0].toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1' + (separator || ','));

    return parts.join('.');
  }
  return NaN;
};

<!--注册 art template 数字格式化显示过滤器 doubleFormat -->
template.defaults.imports.operatorTypeToText = function (value) {
  var text = '导入';
  if (value === "Reimport") {
    text = '重新导入';
  }
  else if (value === 'Modify') {
    text = '修改';
  } else if (value === 'Delete') {
    text = '删除';
  }

  return text;
};

template.defaults.imports.dataSourceTypeToText = function (value) {
  var text = '导入';
  if (value === "CASH") {
    text = '现金收入调查数据';
  }
  else if (value === 'PAYMENT') {
    text = '家庭全年收支调查数据';
  } else if (value === 'RURAL') {
    text = '村表数据';
  }

  return text;
};
