
let menuItems = {
    'menu_id': 'main_menu',
    'items': [ // 主页 船屋菜单 船屋故事 品牌加盟
        {
            text: '主页',
            name: 'home',
            actived: false,
            link: '/'
        },
        {
            text: '船屋菜单',
            name: 'product',
            actived: false,
            link: '/product'
        },
        {
            text: '船屋故事',
            name: 'story',
            actived: false,
            link: '/story'
        },
        {
            text: '品牌加盟',
            name: 'home',
            actived: false,
            link: '/joinus'
        }
    ]

}

let introInfo = {
    'page_id': 'intro',
    'page_title': 'Story',
    'page_api_url': './BoatHouse/EateryStory',
    'page_values': {
        /* text数据库中，以json字符串格式保存到一个字段里 */
        'text': "["
            +"'18世纪90年代末的E国是一个战乱的国度，战争中，名为安德烈（Andre）的皇帝所带领的军队撤离到一个不知名的小镇，受伤的皇帝在这里遇上美丽典雅的牧场少女Aviva，Andre虽然受伤，但身为皇帝的Andre依然心系战事，希望能早日重返战场。',"
            +"'Aviva明白他的想法，除了细心帮他料理伤口，还每天用石头加热烹制菜肴给他吃，因为当地人认为用石头加热烹制食物可以调理身体，对伤口的复原有帮助。后来安德烈（Andre）获得了胜利，为了怀念，Aviva在小镇上开起了一家名为Boat House的小餐馆，里面的菜式全部都是以石头加热烹制的各式菜肴。从此这个小镇改名为南皇后渡口（South Queensferry）。而Boat House餐馆成为了南皇后渡口小镇的标志性建筑。',"
            +"'test Res1',"
            +"'test Res2',"
            +"'test Res3'"
        +"]",

        /* image在数据库中以json字符串格式保存到一个字段里 */
        'image': "["
            +"'../assets/img/story/bg.png',"
            +"'../assets/img/story/left.png',"
            +"'../assets/img/story/right.png',"
            +"'image Res1',"
            +"'image Res2',"
            +"'image Res3'"
        +"]"
    }
}
