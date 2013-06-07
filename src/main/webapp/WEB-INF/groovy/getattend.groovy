log.info "Entering controller 'getattend.groovy'"

request.email = 'example@example.com'
request.autoApply = true

request.description = '''
6月16日（日）　＠A小学校
10:00～11:00　3～5歳（幼稚園・保育園児）　　定員：30名
11:00～12:30　6～7歳（小学1～2年生）　　　　定員：30名
 
6月23日（日）　＠A小学校
10:00～11:00　3～5歳（幼稚園・保育園児）　　定員：30名
11:00～12:30　6～7歳（小学1～2年生）　　　　定員：30名
'''

request.attendMembers = [
  [
    name: '山田　花子',
    printableAge: '小２-7歳',
    number: 0,
    attendList: [
      [
        applied: true,
        dateStr: '20130616',
        printableDate: '6/16',
      ],
      [
        applied: true,
        dateStr: '20130623',
        printableDate: '6/23',
      ],
    ]
  ],
  [
    name: '山田　太郎',
    printableAge: '年中-5歳',
    number: 1,
    attendList: [
      [
        applied: true,
        dateStr: '20130616',
        printableDate: '6/16',
      ],
      [
        applied: true,
        dateStr: '20130623',
        printableDate: '6/23',
      ],
    ]
  ],
]

forward 'attend.gtpl'
