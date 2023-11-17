package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
        //find a way to remove for loop from update quality method
            switch(items[i].name) {
                case "Sulfuras, Hand of Ragnaros":
                    break;
                case "Aged Brie":
                    fixBrie(items[i]);
                    break;
                case "Backstage passes to a TAFKAL80ETC concert":
                    fixBackstagePasses(items[i]);
                    break;
                case "Conjured Mana Cake":
                    fixConjuredManaCake(items[i]);
                    break;
                default:
                    decreaseQuality(items[i]);
                    break;
            } decreaseSellIn(items[i]);
        }
    }
    public void decreaseQuality(Item item) {
        if (item.quality > 0 && item.sellIn > 0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.quality -= 1;
        } else if (item.quality > 0 && item.sellIn < 0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.quality -= 2;
            if (item.quality <= 0) {
                item.quality = 0;
            }
        }

    }

    public void increaseQuality(Item item) {
        if (item.quality < 50 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.quality += 1;
        }
    }

    public void decreaseSellIn(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn--;
        }
    }

    public void fixBrie(Item item) {
        if (item.sellIn >= 0 && item.quality < 50) {
            increaseQuality(item);

        } else if (item.sellIn < 0 && item.quality < 50) {
            increaseQuality(item);
            increaseQuality(item);

        }
    }

    public void fixBackstagePasses(Item item) {
        if (item.sellIn <= 0) {
            item.quality = 0;
        } else if (item.sellIn < 6) {
            increaseQuality(item);
            increaseQuality(item);
            increaseQuality(item);
        } else if (item.sellIn < 11) {
                increaseQuality(item);
                increaseQuality(item);//
        } else {
            increaseQuality(item);

        }
    }


    //Improve method to do it to every conjured item
    // split and place it into array, then check the first index to see
    //if it matches with word "Conjured"?
    public void fixConjuredManaCake(Item item) {
        if (item.name.equals("Conjured Mana Cake")) {

            decreaseQuality(item);
            decreaseQuality(item);
        }
    }

//



}
