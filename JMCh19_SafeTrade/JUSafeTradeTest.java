1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
71
72
73
74
75
76
77
78
79
80
81
82
83
84
85
86
87
88
89
90
91
92
93
94
95
96
97
98
99
100
101
102
103
104
105
106
107
108
109
110
111
112
113
114
115
116
117
118
119
120
121
122
123
124
125
126
127
128
129
130
131
132
133
134
135
136
137
138
139
140
141
142
143
144
145
146
147
148
149
150
151
152
153
154
155
156
157
158
159
160
161
162
163
164
165
166
167
168
169
170
171
172
173
174
175
176
177
178
179
180
181
182
183
184
185
186
187
188
189
190
191
192
193
194
195
196
197
198
199
200
201
202
203
204
205
206
207
208
209
210
211
212
213
214
215
216
217
218
219
220
221
222
223
224
225
226
227
228
229
230
231
232
233
234
235
236
237
238
239
240
241
242
243
244
245
246
247
248
249
250
251
252
253
254
255
256
257
258
259
260
261
262
263
264
265
266
267
268
269
270
271
272
273
274
275
276
277
278
279
280
281
282
283
284
285
286
287
288
289
290
291
292
293
294
295
296
297
298
299
300
301
302
303
304
305
306
307
308
309
310
311
312
313
314
315
316
317
318
319
320
321
322
323
324
325
326
327
328
329
330
331
332
333
334
335
336
337
338
339
340
341
342
343
344
345
346
347
348
349
350
351
352
353
354
355
356
357
358
359
360
361
362
363
364
365
366
367
368
369
370
371
372
373
374
375
376
377
378
379
380
381
382
383
384
385
386
387
388
389
390
391
392
393
394
395
396
397
398
399
400
401
402
403
404
405
406
407
408
409
410
411
412
413
414
415
416
417
418
419
420
421
422
423
424
425
426
427
428
429
430
431
432
433
434
435
436
437
438
439
440
441
442
443
444
445
446
447
448
449
450
451
452
453
454
455
456
457
458
459
460
461
462
463
464
465
466
467
468
469
470
471
472
473
474
475
476
477
478
479
480
481
482
483
484
485
486
487
488
489
490
491
492
493
494
495
496
497
498
499
500
501
502
503
504
505
506
507
508
509
510
511
512
513
514
515
516
517
518
519
520
521
522
523
524
525
526
527
528
529
530
531
532
533
534
535
536
537
538
539
540
541
542
543
544
545
546
547
548
549
550
551
552
553
554
555
556
557
558
559
560
561
562
563
564
565
566
567
568
569
570
571
572
573
574
575
576
577
578
579
580
581
582
583
584
585
586
587
588
589
590
591
592
593
594
595
596
597
598
599
600
601
602
603
604
605
606
607
608
609
610
611
612
613
614
615
616
617
618
619
620
621
622
623
624
625
626
627
628
629
630
631
632
633
634
635
636
637
638
639
640
641
642
643
644
645
646
647
648
649
650
651
652
653
654
655
656
657
658
659
660
661
662
663
664
665
666
667
668
669
670
671
672
673
674
675
676
677
678
679
680
681
682
683
684
685
686
687
688
689
690
691
692
693
694
695
696
697
698
699
700
701
702
703
704
705
706
707
708
709
710
711
712
713
714
715
716
717
718
719
720
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.regex.*;
 
import org.junit.*;
 
import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;
 
 
/**
 * SafeTrade tests: TradeOrder PriceComparator Trader Brokerage StockExchange
 * Stock
 * 
 * @author Raphael Chang (5110720)
 * @author Tommy Chen (5101341)
 * @author Vidur Sanandan (5100960)
 * 
 * @version 1/24/2014
 * @author Assignment: JM Chapter 19 - SafeTrade
 * 
 * @author Sources: None
 * 
 */
public class JUSafeTradeTest
{
    // --Test TradeOrder
    /**
     * TradeOrder tests: TradeOrderConstructor - constructs TradeOrder and then
     * compare toString TradeOrderGetTrader - compares value returned to
     * constructed value TradeOrderGetSymbol - compares value returned to
     * constructed value TradeOrderIsBuy - compares value returned to
     * constructed value TradeOrderIsSell - compares value returned to
     * constructed value TradeOrderIsMarket - compares value returned to
     * constructed value TradeOrderIsLimit - compares value returned to
     * constructed value TradeOrderGetShares - compares value returned to
     * constructed value TradeOrderGetPrice - compares value returned to
     * constructed value TradeOrderSubtractShares - subtracts known value &
     * compares result returned by getShares to expected value
     */
    private String symbol = "GGGL";
 
    private boolean buyOrder = true;
 
    private boolean marketOrder = true;
 
    private int numShares = 123;
 
    private int numToSubtract = 24;
 
    private double price = 123.45;
 
 
    @Test
    public void tradeOrderConstructor()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        String toStr = to.toString();
 
        assertTrue( "<< Invalid TradeOrder Constructor >>",
            toStr.contains( "TradeOrder[Trader trader:null" )
                && toStr.contains( "java.lang.String symbol:" + symbol )
                && toStr.contains( "boolean buyOrder:" + buyOrder )
                && toStr.contains( "boolean marketOrder:" + marketOrder )
                && toStr.contains( "int numShares:" + numShares )
                && toStr.contains( "double price:" + price ) );
    }
 
 
    @Test
    public void TradeOrderToString()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertNotNull( to.toString() );
    }
 
 
    @Test
    public void tradeOrderGetTrader()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertNull( "<< TradeOrder: " + to.getTrader() + " should be null >>",
            to.getTrader() );
    }
 
 
    @Test
    public void tradeOrderGetSymbol()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertEquals( "<< TradeOrder: " + to.getTrader() + " should be "
            + symbol + " >>", symbol, to.getSymbol() );
    }
 
 
    @Test
    public void tradeOrderIsBuy()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
 
        assertTrue( "<< TradeOrder: " + to.isBuy() + " should be " + buyOrder
            + " >>", to.isBuy() );
    }
 
 
    @Test
    public void tradeOrderIsSell()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertFalse( "<< TradeOrder: " + to.isSell() + " should be "
            + !buyOrder + " >>", to.isSell() );
    }
 
 
    @Test
    public void tradeOrderIsMarket()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertTrue( "<< TradeOrder: " + to.isMarket() + " should be "
            + marketOrder + " >>", to.isMarket() );
    }
 
 
    @Test
    public void tradeOrderIsLimit()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
 
        assertFalse( "<< TradeOrder: " + to.isLimit() + " should be "
            + !marketOrder + ">>", to.isLimit() );
    }
 
 
    @Test
    public void tradeOrderGetShares()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertTrue( "<< TradeOrder: " + to.getShares() + " should be "
            + numShares + ">>", numShares == to.getShares()
            || ( numShares - numToSubtract ) == to.getShares() );
    }
 
 
    @Test
    public void tradeOrderGetPrice()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertEquals( "<< TradeOrder: " + to.getPrice() + " should be " + price
            + ">>", price, to.getPrice(), 0.0 );
    }
 
 
    @Test
    public void tradeOrderSubtractShares()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        to.subtractShares( numToSubtract );
        assertEquals( "<< TradeOrder: subtractShares(" + numToSubtract
            + ") should be " + ( numShares - numToSubtract ) + ">>", numShares
            - numToSubtract, to.getShares() );
    }
 
 
    // --Test TraderWindow Stub
    @Test
    public void traderWindowConstructor()
    {
        TraderWindow tw = new TraderWindow( new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" ) );
        assertNotNull( tw );
    }
 
 
    @Test
    public void traderWindowShowMessage()
    {
        TraderWindow tw = new TraderWindow( new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" ) );
        assertNotNull( tw );
        tw.showMessage( null );
    }
 
 
    // --Test PriceComparator
 
    @Test
    public void priceComparatorAscending()
    {
        PriceComparator pc = new PriceComparator();
        TradeOrder to1 = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" ),
            symbol,
            buyOrder,
            false,
            numShares,
            price );
        TradeOrder to2 = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" ),
            symbol,
            buyOrder,
            false,
            numShares,
            price + 5.0 );
        assertNotNull( pc );
        assertNotNull( to1 );
        assertNotNull( to2 );
        assertEquals( "<< PriceComparator: compare(" + to1 + ", " + to2
            + ") should be "
            + (int)( 100 * to1.getPrice() - 100 * to2.getPrice() ) + ">>",
            (int)( 100 * to1.getPrice() - 100 * to2.getPrice() ),
            pc.compare( to1, to2 ) );
    }
 
 
    @Test
    public void priceComparatorDescending()
    {
        PriceComparator pc = new PriceComparator( false );
        TradeOrder to1 = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" ),
            symbol,
            buyOrder,
            false,
            numShares,
            price + 5.0 );
        TradeOrder to2 = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" ),
            symbol,
            buyOrder,
            false,
            numShares,
            price );
        assertNotNull( pc );
        assertNotNull( to1 );
        assertNotNull( to2 );
        assertEquals( "<< PriceComparator: compare(" + to1 + ", " + to2
            + ") should be "
            + (int)( 100 * to2.getPrice() - 100 * to1.getPrice()) + ">>",
            (int)( 100 * to2.getPrice() - 100 * to1.getPrice()),
            pc.compare( to1, to2 ) );
    }
 
 
    // --Test Trader
 
    @Test
    public void traderToString()
    {
        Trader tr = new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" );
        assertNotNull( tr.toString() );
    }
 
 
    @Test
    public void traderGetName()
    {
        Trader tr = new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" );
        assertEquals( tr.getName(), "Test" );
    }
 
 
    @Test
    public void traderGetPassword()
    {
        Trader tr = new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" );
        assertEquals( tr.getPassword(), "Test" );
    }
 
 
    @Test
    public void traderMessage()
    {
        Trader tr = new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" );
        tr.receiveMessage( "Test" );
        assertTrue( tr.hasMessages() );
        tr.openWindow();
        tr.quit();
    }
 
 
    @Test
    public void traderPlaceOrder()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( symbol, "Test", price );
        Stock gggl = exchange.getListedStocks().get( symbol );
        Brokerage broke = new Brokerage( exchange );
        Trader testr = new Trader( broke, "Test", "Test" );
        TradeOrder test = new TradeOrder( testr,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        testr.placeOrder( test );
 
        assertFalse( gggl.getBuyOrders().isEmpty() );
        assertTrue( testr.hasMessages() );
    }
 
 
    @Test
    public void traderGetQuote()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( symbol, "Test", price );
        Stock gggl = exchange.getListedStocks().get( symbol );
        Brokerage broke = new Brokerage( exchange );
        Trader testr = new Trader( broke, "Test", "Test" );
        testr.getQuote( symbol );
 
        assertTrue( testr.hasMessages() );
        testr.mailbox();
    }
 
 
    @Test
    public void traderEquals()
    {
        Trader tr = new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" );
        assertTrue( tr.equals( tr ) );
    }
 
 
    // --Test Brokerage
 
    @Test
    public void brokerageAddUser()
    {
        Brokerage br = new Brokerage( new StockExchange() );
        assertEquals( br.addUser( "T", "Test" ), -1 );
        assertEquals( br.addUser( "Test", "T" ), -2 );
        assertEquals( br.addUser( "Test", "Test" ), 0 );
        assertEquals( br.addUser( "Test", "Test" ), -3 );
        assertTrue( br.getTraders().containsKey( "Test" ) );
    }
 
 
    @Test
    public void brokerageLogin()
    {
        Brokerage br = new Brokerage( new StockExchange() );
        assertEquals( br.addUser( "Test", "Test" ), 0 );
        assertEquals( br.login( "T", "Test" ), -1 );
        assertEquals( br.login( "Test", "T" ), -2 );
        assertEquals( br.login( "Test", "Test" ), 0 );
        assertEquals( br.login( "Test", "Test" ), -3 );
        assertTrue( br.getLoggedTraders().contains( br.getTraders()
            .get( "Test" ) ) );
    }
 
 
    @Test
    public void brokerageGetExchange()
    {
        StockExchange s = new StockExchange();
        Brokerage br = new Brokerage( s );
        assertEquals( br.getExchange(), s );
    }
 
 
    @Test
    public void brokerageLogout()
    {
        Brokerage br = new Brokerage( new StockExchange() );
        assertEquals( br.addUser( "Test", "Test" ), 0 );
        assertEquals( br.login( "Test", "Test" ), 0 );
        br.logout( br.getTraders().get( "Test" ) );
        assertFalse( br.getLoggedTraders().contains( br.getTraders()
            .get( "Test" ) ) );
    }
 
 
    @Test
    public void brokerageGetQuote()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( symbol, "Test", price );
        Stock gggl = exchange.getListedStocks().get( symbol );
        Brokerage broke = new Brokerage( exchange );
        Trader testr = new Trader( broke, "Test", "Test" );
        broke.getQuote( symbol, testr );
 
        assertTrue( testr.hasMessages() );
    }
 
 
    @Test
    public void brokeragePlaceOrder()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( symbol, "Test", price );
        Stock gggl = exchange.getListedStocks().get( symbol );
        Brokerage broke = new Brokerage( exchange );
        Trader testr = new Trader( broke, "Test", "Test" );
        TradeOrder test = new TradeOrder( testr,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        broke.placeOrder( test );
 
        assertFalse( gggl.getBuyOrders().isEmpty() );
        assertTrue( testr.hasMessages() );
    }
 
 
    // --Test StockExchange
    @Test
    public void StockExchangeGetQuote()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( symbol, "Test", price );
        assertEquals( "Test" + " (" + symbol + ")\nPrice: " + price + " hi: "
            + price + " lo: " + price + " vol: 0\n Ask: none Bid: none ",
            exchange.getQuote( symbol ) );
    }
 
 
    @Test
    public void StockExchangeListStock()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( symbol, "Test", price );
        assertEquals( false, exchange.getListedStocks().isEmpty() );
    }
 
 
    @Test
    public void StockExchangePlaceOrder()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock( symbol, "Test", price );
        Stock gggl = exchange.getListedStocks().get( symbol );
        Brokerage broke = new Brokerage( exchange );
        Trader testr = new Trader( broke, "Test", "Test" );
        TradeOrder test = new TradeOrder( testr,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        exchange.placeOrder( test );
 
        assertFalse( gggl.getBuyOrders().isEmpty() );
        assertTrue( testr.hasMessages() );
    }
 
 
    // --Test Stock
    @Test
    public void getQuote()
    {
        Stock stock = new Stock( symbol, "Test", price );
        assertEquals( stock.getQuote(), "Test (" + symbol + ")\nPrice: "
            + price + " hi: " + price + " lo: " + price
            + " vol: 0\n Ask: none Bid: none " );
    }
 
 
    @Test
    public void getBuyStockQuote()
    {
        Stock stock = new Stock( symbol, "Test", price );
        TradeOrder to = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" ),
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        stock.placeOrder( to );
 
        assertEquals( "Test (" + symbol + ")\nPrice: " + price + " hi: "
            + price + " lo: " + price
            + " vol: 0\n Ask: none Bid: " + price + " size: 123", stock.getQuote() );
    }
 
 
    @Test
    public void getSellStockQuote()
    {
        Stock stock = new Stock( symbol, "Test", price );
        TradeOrder to = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" ),
            symbol,
            false,
            marketOrder,
            numShares,
            price );
        stock.placeOrder( to );
 
        assertEquals( "Test (" + symbol + ")\nPrice: " + price + " hi: "
            + price + " lo: " + price
            + " vol: 0\n Ask: " + price + " size: 123 Bid: none ", stock.getQuote() );
    }
 
 
    @Test
    public void getSellLimitStockQuote()
    {
        Stock stock = new Stock( symbol, "Test", price );
        TradeOrder to = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" ),
            symbol,
            false,
            false,
            numShares,
            price );
        stock.placeOrder( to );
 
        assertEquals( "Test (" + symbol + ")\nPrice: " + price + " hi: "
            + price + " lo: " + price
            + " vol: 0\n Ask: 123.45 size: 123 Bid: none ", stock.getQuote() );
    }
 
 
    @Test
    public void getSellMarketStockQuote()
    {
        Stock stock = new Stock( symbol, "Test", price );
        buyOrder = false;
        assertEquals( stock.getQuote(), "Test (" + symbol + ")\nPrice: "
            + price + " hi: " + price + " lo: " + price
            + " vol: 0\n Ask: none Bid: none " );
    }
 
 
    @Test
    public void placeStockBuyOrder()
    {
        StockExchange exchange = new StockExchange();
        Stock gggl = new Stock( symbol, "Test", price );
        exchange.listStock( symbol, "Test", price );
        Brokerage broke = new Brokerage( exchange );
        Trader testr = new Trader( broke, "Test", "Test" );
        TradeOrder test = new TradeOrder( testr,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
 
        gggl.placeOrder( test );
        assertFalse( gggl.getBuyOrders().isEmpty() );
        assertTrue( testr.hasMessages() );
    }
 
 
    @Test
    public void placeStockSellOrder()
    {
        StockExchange exchange = new StockExchange();
        Stock gggl = new Stock( symbol, "Test", price );
        exchange.listStock( symbol, "Test", price );
        Brokerage broke = new Brokerage( exchange );
        Trader testr = new Trader( broke, "Test", "Test" );
        TradeOrder test = new TradeOrder( testr,
            symbol,
            false,
            marketOrder,
            numShares,
            price );
 
        gggl.placeOrder( test );
        assertFalse( gggl.getSellOrders().isEmpty() );
        assertTrue( testr.hasMessages() );
    }
 
 
    @Test
    public void toStringTest()
    {
        StockExchange exchange = new StockExchange();
        Stock gggl = new Stock( symbol, "Test", price );
        Trader tr = new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" );
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        Brokerage br = new Brokerage( new StockExchange() );
        assertNotNull( exchange.toString() );
        assertNotNull( gggl.toString() );
        assertNotNull( tr.toString() );
        assertNotNull( to.toString() );
        assertNotNull( br.toString() );
    }
 
 
    @Test
    public void executeOrders()
    {
        StockExchange exchange = new StockExchange();
        Stock gggl = new Stock( symbol, "Test", price );
        exchange.listStock( symbol, "Test", price );
        Brokerage broke = new Brokerage( exchange );
        Trader testr = new Trader( broke, "Test", "Test" );
        TradeOrder test = new TradeOrder( testr,
            symbol,
            false,
            marketOrder,
            numShares,
            price );
        TradeOrder test2 = new TradeOrder( testr,
            symbol,
            true,
            marketOrder,
            numShares,
            price );
 
        gggl.placeOrder( test );
        gggl.placeOrder( test2 );
        assertTrue( testr.hasMessages() );
        gggl.executeOrders();
    }
 
 
    @Test
    public void stockGetMethods()
    {
        Stock gggl = new Stock( symbol, "Test", price );
        assertEquals( gggl.getStockSymbol(), symbol );
        assertEquals( gggl.getCompanyName(), "Test" );
        assertEquals( (int)gggl.getLoPrice(), (int)price );
        assertEquals( (int)gggl.getHiPrice(), (int)price );
        assertEquals( (int)gggl.getLastPrice(), (int)price );
        assertEquals( (int)gggl.getVolume(), 0 );
    }
 
    // Remove block comment below to run JUnit test in console
    
      public static junit.framework.Test suite() { return new
      JUnit4TestAdapter( JUSafeTradeTest.class ); }
      
      public static void main( String args[] ) {
      org.junit.runner.JUnitCore.main( "JUSafeTradeTest" ); }
     
}


//import java.util.Map;
//import java.util.PriorityQueue;
//import java.util.Queue;
//import java.util.Set;
//import java.util.regex.*;
//
//import org.junit.*;
//
//import static org.junit.Assert.*;
//import junit.framework.JUnit4TestAdapter;
////_____   ____ _______       _    _ _______ ____  
////|  __ \ / __ \__   __|/\   | |  | |__   __/ __ \ 
////| |__) | |  | | | |  /  \  | |__| |  | | | |  | |
////|  ___/| |  | | | | / /\ \ |  __  |  | | | |  | |
////| |    | |__| | | |/ ____ \| |  | |  | | | |__| |
////|_|     \____/  |_/_/    \_\_|  |_|  |_|  \____/ 
////                                              
////
///**
// * SafeTrade tests:
// *   TradeOrder
// *   PriceComparator
// *   Trader
// *   Brokerage
// *   StockExchange
// *   Stock
// *
// * @author TODO Name of principal author
// * @author TODO Name of group member
// * @author TODO Name of group member
// * @version TODO date
// * @author Assignment: JM Chapter 19 - SafeTrade
// * 
// * @author Sources: TODO sources
// *
// */
//public class JUSafeTradeTest
//{
//    // --Test TradeOrder
//    /**
//     * TradeOrder tests:
//     *   TradeOrderConstructor - constructs TradeOrder and then compare toString
//     *   TradeOrderGetTrader - compares value returned to constructed value
//     *   TradeOrderGetSymbol - compares value returned to constructed value
//     *   TradeOrderIsBuy - compares value returned to constructed value
//     *   TradeOrderIsSell - compares value returned to constructed value
//     *   TradeOrderIsMarket - compares value returned to constructed value
//     *   TradeOrderIsLimit - compares value returned to constructed value
//     *   TradeOrderGetShares - compares value returned to constructed value
//     *   TradeOrderGetPrice - compares value returned to constructed value
//     *   TradeOrderSubtractShares - subtracts known value & compares result
//     *     returned by getShares to expected value
//     */
//    private String symbol = "GGGL";
//    private boolean buyOrder = true;
//    private boolean marketOrder = true;
//    private int numShares = 123;
//    private int numToSubtract = 24;
//    private double price = 123.45;
//
//    @Test
//    public void tradeOrderConstructor()
//    {
//        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
//            numShares, price );
//        String toStr = to.toString();
//
//        assertTrue( "<< Invalid TradeOrder Constructor >>",
//                    toStr.contains( "TradeOrder[Trader trader:null" )
//                        && toStr.contains( "java.lang.String symbol:" + symbol )
//                        && toStr.contains( "boolean buyOrder:" + buyOrder )
//                        && toStr.contains( "boolean marketOrder:" + marketOrder )
//                        && toStr.contains( "int numShares:" + numShares )
//                        && toStr.contains( "double price:" + price ) );
//    }
//    
//    @Test
//    public void TradeOrderToString()
//    {
//        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
//            numShares, price );
//        assertNotNull( to.toString() );
//    }
//
//    @Test
//    public void tradeOrderGetTrader()
//    {
//        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
//            numShares, price );
//        assertNull( "<< TradeOrder: " + to.getTrader() + " should be null >>",
//                    to.getTrader() );
//    }
//
//    @Test
//    public void tradeOrderGetSymbol()
//    {
//        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
//            numShares, price );
//        assertEquals( "<< TradeOrder: " + to.getTrader() + " should be "
//            + symbol + " >>", symbol, to.getSymbol() );
//    }
//
//    @Test
//    public void tradeOrderIsBuy()
//    {
//        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
//            numShares, price );
//
//        assertTrue( "<< TradeOrder: " + to.isBuy() + " should be " + buyOrder
//            + " >>", to.isBuy() );
//    }
//
//    @Test
//    public void tradeOrderIsSell()
//    {
//        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
//            numShares, price );
//        assertFalse( "<< TradeOrder: " + to.isSell() + " should be "
//            + !buyOrder + " >>", to.isSell() );
//    }
//
//    @Test
//    public void tradeOrderIsMarket()
//    {
//        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
//            numShares, price );
//        assertTrue( "<< TradeOrder: " + to.isMarket() + " should be "
//            + marketOrder + " >>", to.isMarket() );
//    }
//
//    @Test
//    public void tradeOrderIsLimit()
//    {
//        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
//            numShares, price );
//
//        assertFalse( "<< TradeOrder: " + to.isLimit() + " should be "
//            + !marketOrder + ">>", to.isLimit() );
//    }
//
//    @Test
//    public void tradeOrderGetShares()
//    {
//        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
//            numShares, price );
//        assertTrue( "<< TradeOrder: " + to.getShares() + " should be "
//            + numShares + ">>", numShares == to.getShares()
//            || ( numShares - numToSubtract ) == to.getShares() );
//    }
//
//    @Test
//    public void tradeOrderGetPrice()
//    {
//        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
//            numShares, price );
//        assertEquals( "<< TradeOrder: " + to.getPrice() + " should be " + price
//            + ">>", price, to.getPrice(), 0.0 );
//    }
//
//    @Test
//    public void tradeOrderSubtractShares()
//    {
//        TradeOrder to = new TradeOrder( null, symbol, buyOrder, marketOrder,
//            numShares, price );
//        to.subtractShares( numToSubtract );
//        assertEquals( "<< TradeOrder: subtractShares(" + numToSubtract
//            + ") should be " + ( numShares - numToSubtract ) + ">>", numShares
//            - numToSubtract, to.getShares() );
//    }
//    
//    // --Test TraderWindow Stub
//    @Test
//    public void traderWindowConstructor()
//    {
//        TraderWindow tw = new TraderWindow( null );
//        assertNotNull( tw );
//    }
//
//    @Test
//    public void traderWindowShowMessage()
//    {
//        TraderWindow tw = new TraderWindow( null );
//        assertNotNull( tw );
//        tw.showMessage( null );
//    }
//
//    //  --Test PriceComparator
//    /*
//     * Price Comparator Tests:
//     *      priceComparatorCompare()
//     */
//    public void priceComparatorAscending()
//    {
//        PriceComparator pc = new PriceComparator();
//        TradeOrder t1 = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
//            "Test",
//            "Test" ),
//            symbol,
//            buyOrder,
//            false,
//            numShares,
//            price );
//        TradeOrder t2 = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
//            "Test",
//            "Test" ),
//            symbol,
//            buyOrder,
//            false,
//            numShares,
//            price + 5.0 );
//        assertNotNull( pc );
//        assertNotNull( t1 );
//        assertNotNull( t2 );
//        assertEquals( "<< PriceComparator: compare(" + t1 + ", " + t2
//            + ") should be " + (int)Math.round( (100 * t1.getPrice()) - (100 * t2.getPrice()) )
//            + ">>",
//            (int)Math.round( (100 * t1.getPrice()) - (100 * t2.getPrice()) ),
//            pc.compare( t1, t2 ) );
//    }
// 
// 
//    @Test
//    public void priceComparatorDescending()
//    {
//        PriceComparator pc = new PriceComparator( false );
//        TradeOrder t1 = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
//            "Test",
//            "Test" ),
//            symbol,
//            buyOrder,
//            false,
//            numShares,
//            price + 5.0 );
//        TradeOrder t2 = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
//            "Test",
//            "Test" ),
//            symbol,
//            buyOrder,
//            false,
//            numShares,
//            price );
//        assertNotNull( pc );
//        assertNotNull( t1 );
//        assertNotNull( t2 );
//        assertEquals( "<< PriceComparator: compare(" + t1 + ", " + t2
//            + ") should be " + (int)Math.round(( 100 * t2.getPrice()) - (100 * t1.getPrice()) )
//            + ">>",
//            (int)Math.round( (100 * t2.getPrice()) - (100 * t1.getPrice()) ),
//            pc.compare( t1, t2 ) );
//    }
//    
//    // --Test Trader 
//    @Test
//    public void traderEquals()
//    {
//        Trader tr = new Trader( new Brokerage( new StockExchange() ),
//            "Test",
//            "Test" );
//        assertTrue( tr.equals( tr ) );
//    }
//    @Test
//    public void traderGetName()
//    {
//        Trader tr = new Trader( new Brokerage( new StockExchange() ),
//            "Test",
//            "Test" );
//        assertEquals( tr.getName(), "Test" );
//    }
// 
// 
//    @Test
//    public void traderGetPassword()
//    {
//        Trader tr = new Trader( new Brokerage( new StockExchange() ),
//            "Test",
//            "Test" );
//        assertEquals( tr.getPassword(), "Test" );
//    }
//    public void traderGetQuote()
//    {
//        StockExchange exchange = new StockExchange();
//        exchange.listStock( symbol, "Test", price );
//        Stock gggl = exchange.getListedStocks().get( symbol );
//        Brokerage broke = new Brokerage( exchange );
//        Trader testr = new Trader( broke, "Test", "Test" );
//        testr.getQuote( symbol );
// 
//        assertTrue( testr.hasMessages() );
//        testr.mailbox();
//    }
// 
//    @Test
//    public void traderMessage()
//    {
//        Trader tr = new Trader( new Brokerage( new StockExchange() ),
//            "Test",
//            "Test" );
//        tr.receiveMessage( "Test" );
//        assertTrue( tr.hasMessages() );
//        tr.openWindow();
//        assertFalse( tr.hasMessages() );
//        tr.quit();
//    }
// 
// 
//    @Test
//    public void traderPlaceOrder()
//    {
//        StockExchange exchange = new StockExchange();
//        exchange.listStock( symbol, "Test", price );
//        Stock gggl = exchange.getListedStocks().get( symbol );
//        Brokerage broke = new Brokerage( exchange );
//        Trader testr = new Trader( broke, "Test", "Test" );
//        TradeOrder test = new TradeOrder( testr,
//            symbol,
//            buyOrder,
//            marketOrder,
//            numShares,
//            price );
//        testr.placeOrder( test );
// 
//        assertFalse( gggl.getBuyOrders().isEmpty() );
//        assertTrue( testr.hasMessages() );
//    }  
//    
//    public void traderToString()
//    {
//        Trader t = new Trader( new Brokerage( new StockExchange() ),
//            "Test",
//            "Test" );
//        assertNotNull( t.toString() );
//    }
//    // TODO your tests here
//    
//    
//    // --Test Brokerage
//    public void brokerageAddUser()
//    {
//        Brokerage b = new Brokerage( new StockExchange() );
//        assertEquals( b.addUser( "T", "Test" ), -1 );
//        assertEquals( b.addUser( "Test", "T" ), -2 );
//        assertEquals( b.addUser( "Test", "Test" ), 0 );
//        assertEquals( b.addUser( "Test", "Test" ), -3 );
//        assertTrue( b.getTraders().containsKey( "Test" ) );
//    }
//    
//    @Test
//    public void brokerageGetQuote()
//    {
//        StockExchange exchange = new StockExchange();
//        exchange.listStock( symbol, "Test", price );
//        Stock s = exchange.getListedStocks().get( symbol );
//        Brokerage broke = new Brokerage( exchange );
//        Trader t = new Trader( broke, "Test", "Test" );
//        broke.getQuote( symbol, t );
// 
//        assertTrue( t.hasMessages() );
//    }
// 
//    @Test
//    public void brokerageLogin()
//    {
//        Brokerage b = new Brokerage( new StockExchange() );
//        assertEquals( b.addUser( "Test", "Test" ), 0 );
//        assertEquals( b.login( "Test", "Test" ), 0 );
//        assertEquals( b.login( "T", "Test" ), -1 );
//        assertEquals( b.login( "Test", "T" ), -2 );  
//        assertEquals( b.login( "Test", "Test" ), -3 );
//        assertTrue( b.getLoggedTraders().contains( b.getTraders()
//            .get( "Test" ) ) );
//    }
// 
// 
//    @Test
//    public void brokerageGetExchange()
//    {
//        StockExchange s = new StockExchange();
//        Brokerage b = new Brokerage( s );
//        assertEquals( b.getExchange(), s );
//    }
// 
// 
//    @Test
//    public void brokerageLogout()
//    {
//        Brokerage b = new Brokerage( new StockExchange() );
//        assertEquals( b.login( "Test", "Test" ), 0 );
//        assertEquals( b.addUser( "Test", "Test" ), 0 );
//        b.logout( b.getTraders().get( "Test" ) );
//        assertFalse( b.getLoggedTraders().contains( b.getTraders()
//            .get( "Test" ) ) );
//    }
// 
// 
//    
// 
// 
//    @Test
//    public void brokeragePlaceOrder()
//    {
//        StockExchange exchange = new StockExchange();
//        exchange.listStock( symbol, "Test", price );
//        Stock gggl = exchange.getListedStocks().get( symbol );
//        Brokerage broke = new Brokerage( exchange );
//        Trader testr = new Trader( broke, "Test", "Test" );
//        TradeOrder test = new TradeOrder( testr,
//            symbol,
//            buyOrder,
//            marketOrder,
//            numShares,
//            price );
//        broke.placeOrder( test );
// 
//        assertFalse( gggl.getBuyOrders().isEmpty() );
//        assertTrue( testr.hasMessages() );
//    }
//    // TODO your tests here
//    
//    
//    // --Test StockExchange
//    public void StockExchangeGetQuote()
//    {
//        StockExchange exchange = new StockExchange();
//        exchange.listStock( symbol, "Test", price );
//        assertEquals( "Test" + " (" + symbol + ")\nPrice: " + price + "  hi: "
//            + price + "  lo: " + price + "  vol: 0\nAsk: none Bid: none",
//            exchange.getQuote( symbol ) );
//    }
// 
// 
//    @Test
//    public void StockExchangeListStock()
//    {
//        StockExchange exchange = new StockExchange();
//        exchange.listStock( symbol, "Test", price );
//        assertEquals( false, exchange.getListedStocks().isEmpty() );
//    }
// 
// 
//    @Test
//    public void StockExchangePlaceOrder()
//    {
//        StockExchange exchange = new StockExchange();
//        exchange.listStock( symbol, "Test", price );
//        Stock gggl = exchange.getListedStocks().get( symbol );
//        Brokerage broke = new Brokerage( exchange );
//        Trader testr = new Trader( broke, "Test", "Test" );
//        TradeOrder test = new TradeOrder( testr,
//            symbol,
//            buyOrder,
//            marketOrder,
//            numShares,
//            price );
//        exchange.placeOrder( test );
// 
//        assertFalse( gggl.getBuyOrders().isEmpty() );
//        assertTrue( testr.hasMessages() );
//    }
//    // TODO your tests here
//    
//    
//    // --Test Stock
//    public void getQuote()
//    {
//        Stock stock = new Stock( symbol, "Test", price );
//        assertEquals( stock.getQuote(), "Test (" + symbol + ")\nPrice: "
//            + price + "  hi: " + price + "  lo: " + price
//            + "  vol: 0\nAsk: none Bid: none" );
//    }
// 
// 
//    @Test
//    public void getBuyStockQuote()
//    {
//        Stock stock = new Stock( symbol, "Test", price );
//        TradeOrder to = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
//            "Test",
//            "Test" ),
//            symbol,
//            false,
//            buyOrder,
//            numShares,
//            price );
//        stock.placeOrder( to );
// 
//        assertEquals( "Test (" + symbol + ")\nPrice: " + price + "  hi: "
//            + price + "  lo: " + price
//            + "  vol: 0\nAsk: market size: 123 Bid: none", stock.getQuote() );
//    }
// 
// 
//    @Test
//    public void getSellStockQuote()
//    {
//        Stock stock = new Stock( symbol, "Test", price );
//        TradeOrder to = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
//            "Test",
//            "Test" ),
//            symbol,
//            marketOrder,
//            buyOrder,
//            numShares,
//            price );
//        stock.placeOrder( to );
// 
//        assertEquals( "Test (" + symbol + ")\nPrice: " + price + "  hi: "
//            + price + "  lo: " + price
//            + "  vol: 0\nAsk: market size: 123 Bid: none", stock.getQuote() );
//    }
// 
// 
//    @Test
//    public void getSellLimitStockQuote()
//    {
//        Stock stock = new Stock( symbol, "Test", price );
//        TradeOrder to = new TradeOrder( new Trader( new Brokerage( new StockExchange() ),
//            "Test",
//            "Test" ),
//            symbol,
//            false,
//            false,
//            numShares,
//            price );
//        stock.placeOrder( to );
// 
//        assertEquals( "Test (" + symbol + ")\nPrice: " + price + "  hi: "
//            + price + "  lo: " + price
//            + "  vol: 0\nAsk: 123.45 size: 123 Bid: none", stock.getQuote() );
//    }
// 
// 
//    @Test
//    public void getSellMarketStockQuote()
//    {
//        Stock stock = new Stock( symbol, "Test", price );
//        buyOrder = false;
//        assertEquals( stock.getQuote(), "Test (" + symbol + ")\nPrice: "
//            + price + "  hi: " + price + "  lo: " + price
//            + "  vol: 0\nAsk: none Bid: none" );
//    }
// 
// 
//    @Test
//    public void placeStockBuyOrder()
//    {
//        StockExchange exchange = new StockExchange();
//        Stock gggl = new Stock( symbol, "Test", price );
//        exchange.listStock( symbol, "Test", price );
//        Brokerage broke = new Brokerage( exchange );
//        Trader testr = new Trader( broke, "Test", "Test" );
//        TradeOrder test = new TradeOrder( testr,
//            symbol,
//            buyOrder,
//            marketOrder,
//            numShares,
//            price );
// 
//        gggl.placeOrder( test );
//        assertFalse( gggl.getBuyOrders().isEmpty() );
//        assertTrue( testr.hasMessages() );
//    }
// 
// 
//    @Test
//    public void placeStockSellOrder()
//    {
//        StockExchange exchange = new StockExchange();
//        Stock gggl = new Stock( symbol, "Test", price );
//        exchange.listStock( symbol, "Test", price );
//        Brokerage broke = new Brokerage( exchange );
//        Trader testr = new Trader( broke, "Test", "Test" );
//        TradeOrder test = new TradeOrder( testr,
//            symbol,
//            false,
//            marketOrder,
//            numShares,
//            price );
// 
//        gggl.placeOrder( test );
//        assertFalse( gggl.getSellOrders().isEmpty() );
//        assertTrue( testr.hasMessages() );
//    }
// 
// 
//    @Test
//    public void toStringTest()
//    {
//        StockExchange exchange = new StockExchange();
//        Stock gggl = new Stock( symbol, "Test", price );
//        Trader tr = new Trader( new Brokerage( new StockExchange() ),
//            "Test",
//            "Test" );
//        TradeOrder to = new TradeOrder( null,
//            symbol,
//            buyOrder,
//            marketOrder,
//            numShares,
//            price );
//        Brokerage br = new Brokerage( new StockExchange() );
//        assertNotNull( exchange.toString() );
//        assertNotNull( gggl.toString() );
//        assertNotNull( tr.toString() );
//        assertNotNull( to.toString() );
//        assertNotNull( br.toString() );
//    }
// 
// 
//    @Test
//    public void executeOrders()
//    {
//        StockExchange exchange = new StockExchange();
//        Stock gggl = new Stock( symbol, "Test", price );
//        exchange.listStock( symbol, "Test", price );
//        Brokerage broke = new Brokerage( exchange );
//        Trader testr = new Trader( broke, "Test", "Test" );
//        TradeOrder test = new TradeOrder( testr,
//            symbol,
//            false,
//            marketOrder,
//            numShares,
//            price );
//        TradeOrder test2 = new TradeOrder( testr,
//            symbol,
//            true,
//            marketOrder,
//            numShares,
//            price );
// 
//        gggl.placeOrder( test );
//        gggl.placeOrder( test2 );
//        assertFalse( gggl.getSellOrders().isEmpty() );
//        assertTrue( testr.hasMessages() );
//        gggl.executeOrders();
//    }
// 
// 
//    @Test
//    public void stockGetMethods()
//    {
//        Stock gggl = new Stock( symbol, "Test", price );
//        assertEquals( gggl.getStockSymbol(), symbol );
//        assertEquals( gggl.getCompanyName(), "Test" );
//        assertEquals( (int)gggl.getLoPrice(), (int)price );
//        assertEquals( (int)gggl.getHiPrice(), (int)price );
//        assertEquals( (int)gggl.getLastPrice(), (int)price );
//        assertEquals( (int)gggl.getVolume(), 0 );
//    }
//    // TODO your tests here
//
//    
//    // Remove block comment below to run JUnit test in console
//
//    public static junit.framework.Test suite()
//    {
//        return new JUnit4TestAdapter( JUSafeTradeTest.class );
//    }
//    
//    public static void main( String args[] )
//    {
//        org.junit.runner.JUnitCore.main( "JUSafeTradeTest" );
//    }
//
//}
