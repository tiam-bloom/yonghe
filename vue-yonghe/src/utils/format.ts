import moment from 'moment';

/*日期处理*/
function dateFormat(row: any, column: any) {
    const date = row[column.property];
    return moment(date).format("YYYY-MM-DD HH:mm:ss")
}

// 金额处理
function priceFormat(row: any, column: any) {
    const price = row[column.property].toFixed(2);
    return `￥${price}`
}

export { dateFormat, priceFormat }