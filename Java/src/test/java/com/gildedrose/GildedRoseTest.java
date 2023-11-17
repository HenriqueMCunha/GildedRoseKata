package com.gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    public static GildedRose app;
    static void set() {
        Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
            new Item("Conjured Mana Cake", 3, 6)};

        app = new GildedRose(items);
        app.updateQuality();

    }

    @BeforeAll
    static void setUpAll() {
        set();
    }


    @Test
    @DisplayName("DexterityVest")
    void dexterityVest(){
        Assertions.assertEquals("+5 Dexterity Vest", app.items[0].name);
    }

    @Test
    @DisplayName("Aged Brie")
    void agedBrie(){
        Assertions.assertEquals("Aged Brie", app.items[1].name);
    }

    @Test
    @DisplayName("Brie increases by one before SellIn goes under 0")
    void brieBeforeSellIn0() {
        Item testItem = new Item("Aged Brie", 5, 6);
        app.fixBrie(testItem);
        Assertions.assertEquals(7, testItem.quality);
    }
    @Test
    @DisplayName("Brie increases by two after SellIn goes under 0")
    void brieAfterSellIn0() {
        Item testItem = new Item("Aged Brie", -1, 7);
        app.fixBrie(testItem);
        Assertions.assertEquals(9, testItem.quality);
    }

    @Test
    @DisplayName("Sulfuras does not increase Quality Before SellIn 0")
    void sulfurasBeforeSellIn0() {
        Item testItem = new Item("Sulfuras, Hand of Ragnaros", 7, 80);
        app.decreaseQuality(testItem);
        Assertions.assertEquals(80, testItem.quality);
    }

    @Test
    @DisplayName("Sulfuras does not increase Quality After SellIn 0")
    void sulfurasAfterSellIn0() {
        Item testItem = new Item("Sulfuras, Hand of Ragnaros", -3, 80);
        app.decreaseQuality(testItem);
        Assertions.assertEquals(80, testItem.quality);
    }

    @Test
    @DisplayName("Sulfuras does not change sellIn value")
    void sulfurasSellInChange() {
        Item testItem = new Item("Sulfuras, Hand of Ragnaros", 4, 90);
        app.decreaseSellIn(testItem);
        Assertions.assertEquals(4, testItem.sellIn);
    }

    @Test
    @DisplayName("Conjured Mana Cake goes down by two before SellIn 0")
    void conjuredBeforeSellIn0() {
        Item testItem = new Item("Conjured Mana Cake", 5, 8);
        app.fixConjuredManaCake(testItem);
        Assertions.assertEquals(6, testItem.quality);
    }

    @Test
    @DisplayName("Conjured Mana Cake goes down by 4 after SellIn 0")
    void conjureAfterSellIn0() {
        Item testItem = new Item("Conjured Mana Cake", -3, 8);
        app.fixConjuredManaCake(testItem);
        Assertions.assertEquals(4, testItem.quality);
    }

    @Test
    @DisplayName("Dexterity vest before sellIn 0")
    void dexterityVestBeforeSellIn0() {
        Item testItem = new Item("+5 Dexterity Vest", 6, 20);
        app.decreaseQuality(testItem);
        Assertions.assertEquals(19, testItem.quality);
    }
    @Test
    @DisplayName("Dexterity vest after sellIn 0")
    void dexterityVestAfterSellIn0() {
        Item testItem = new Item("+5 Dexterity Vest", -5, 20);
        app.decreaseQuality(testItem);
        Assertions.assertEquals(18, testItem.quality);
    }

    @Test
    @DisplayName("Dexterity vest quality set at 0")
    void dexterityVestQualitySetAt0() {
        Item testItem = new Item("+5 Dexterity Vest", -5, 1);
        app.decreaseQuality(testItem);
        Assertions.assertEquals(0, testItem.quality);
    }


    @Test
    @DisplayName("Passes update value before 10 sell by date")
    void passesQuality1Days() {
        Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 45);
        app.fixBackstagePasses(testItem);
        Assertions.assertEquals(48, testItem.quality);
    }

    @Test
    @DisplayName("Passes update value on day 6 sell by date")
    void passesQuality6Days() {
        Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", 6, 21);
        app.fixBackstagePasses(testItem);
        Assertions.assertEquals(23, testItem.quality);
    }

    @Test
    @DisplayName("Passes update value on day 16 sell by date")
    void passesQuality16Days() {
        Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", 16, 16);
        app.fixBackstagePasses(testItem);
        Assertions.assertEquals(17, testItem.quality);
    }

    @Test
    @DisplayName("Passes update value on day -1 sell by date")
    void passesQualityMinus1Days() {
        Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 21);
        app.fixBackstagePasses(testItem);
        Assertions.assertEquals(0, testItem.quality);
    }

    @Test
    @DisplayName("Quality stays in value 50")
    void passesQualityOver50() {
        Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", 3, 49);
        app.fixBackstagePasses(testItem);
        Assertions.assertEquals(50, testItem.quality);
    }




    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }





}
