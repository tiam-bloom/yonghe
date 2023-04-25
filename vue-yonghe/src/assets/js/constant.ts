const orderTypeOptions = [
    {
        value: '堂食',
        label: '堂食'
    },
    {
        value: '外卖',
        label: '外卖'
    },
    {
        value: '打包',
        label: '打包'
    }
]
const payTypeOptions = [
    {
        value: '微信支付',
        label: '微信支付'
    },
    {
        value: '支付宝',
        label: '支付宝'
    },
    {
        value: '现金',
        label: '现金'
    }
]
interface Order {
    door?: {
        id: number,
        name: string
    },
    id?: number,
    doorId: number,
    orderNo: string,
    orderType: string,
    pnum: number,
    cashier: string,
    orderTime?: string,
    payTime?: string,
    payType: string,
    price: number
}

interface Door {
    id?: number,
    name: string
    tel: string
    addr: string
}
export { orderTypeOptions, payTypeOptions }
export type { Order, Door }