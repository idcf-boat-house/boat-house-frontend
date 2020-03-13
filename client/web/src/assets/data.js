
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
    'page_title': 'BoatHouse\'s Story',
    'page_api_url': './BoatHouse/EateryStory',
    'page_values': {
        /* text数据库中，以json字符串格式保存到一个字段里 */
        'text': [
            'test1 in left',
            'text2 in right',
            'test3 预留，没有文本时为空字符串',
            'test4 预留，没有文本时为空字符串',
            'test5 预留，没有文本时为空字符串'
        ],

        /* image在数据库中以json字符串格式保存到一个字段里 */
        'image': [
            'background image\'s url',
            'left image\'s url',
            'right image\'s url',
            'image 预留，没有图片时为空字符串',
            'image 预留，没有图片时为空字符串',
            'image 预留，没有图片时为空字符串'
        ]
    }
}
