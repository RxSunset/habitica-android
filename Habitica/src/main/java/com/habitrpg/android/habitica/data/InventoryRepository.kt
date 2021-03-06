package com.habitrpg.android.habitica.data

import com.habitrpg.android.habitica.models.inventory.Egg
import com.habitrpg.android.habitica.models.inventory.Equipment
import com.habitrpg.android.habitica.models.inventory.Food
import com.habitrpg.android.habitica.models.inventory.HatchingPotion
import com.habitrpg.android.habitica.models.inventory.Item
import com.habitrpg.android.habitica.models.inventory.Mount
import com.habitrpg.android.habitica.models.inventory.Pet
import com.habitrpg.android.habitica.models.inventory.Quest
import com.habitrpg.android.habitica.models.inventory.QuestContent
import com.habitrpg.android.habitica.models.responses.BuyResponse
import com.habitrpg.android.habitica.models.responses.FeedResponse
import com.habitrpg.android.habitica.models.shops.Shop
import com.habitrpg.android.habitica.models.shops.ShopItem
import com.habitrpg.android.habitica.models.user.Items
import com.habitrpg.android.habitica.models.user.User

import io.reactivex.Flowable
import io.realm.RealmResults


interface InventoryRepository : ContentRepository {

    fun getArmoireRemainingCount(): Long

    fun getInAppRewards(): Flowable<RealmResults<ShopItem>>
    fun getOwnedEquipment(): Flowable<RealmResults<Equipment>>

    fun getMounts(): Flowable<RealmResults<Mount>>

    fun getOwnedMounts(): Flowable<RealmResults<Mount>>

    fun getPets(): Flowable<RealmResults<Pet>>

    fun getOwnedPets(): Flowable<RealmResults<Pet>>
    fun getQuestContent(key: String): Flowable<QuestContent>

    fun getItems(searchedKeys: List<String>): Flowable<RealmResults<Equipment>>
    fun retrieveInAppRewards(): Flowable<List<ShopItem>>

    fun getOwnedEquipment(type: String): Flowable<RealmResults<Equipment>>

    fun getOwnedItems(itemClass: Class<out Item>, user: User?): Flowable<out RealmResults<out Item>>
    fun getOwnedItems(user: User): Flowable<out Map<String, Item>>

    fun getEquipment(key: String): Flowable<Equipment>

    fun openMysteryItem(user: User?): Flowable<Equipment>

    fun saveEquipment(equipment: Equipment)
    fun getMounts(type: String, group: String): Flowable<RealmResults<Mount>>
    fun getOwnedMounts(animalType: String, animalGroup: String): Flowable<RealmResults<Mount>>
    fun getPets(type: String, group: String): Flowable<RealmResults<Pet>>
    fun getOwnedPets(type: String, group: String): Flowable<RealmResults<Pet>>

    fun updateOwnedEquipment(user: User)

    fun changeOwnedCount(type: String, key: String, amountToAdd: Int)

    fun sellItem(user: User?, type: String, key: String): Flowable<User>
    fun sellItem(user: User?, item: Item): Flowable<User>

    fun equipGear(user: User?, equipment: String, asCostume: Boolean): Flowable<Items>
    fun equip(user: User?, type: String, key: String): Flowable<Items>

    fun feedPet(pet: Pet, food: Food): Flowable<FeedResponse>

    fun hatchPet(egg: Egg, hatchingPotion: HatchingPotion): Flowable<Items>

    fun inviteToQuest(quest: QuestContent): Flowable<Quest>

    fun buyItem(user: User?, id: String, value: Double): Flowable<BuyResponse>

    fun retrieveShopInventory(identifier: String): Flowable<Shop>
    fun retrieveMarketGear(): Flowable<Shop>

    fun purchaseMysterySet(categoryIdentifier: String): Flowable<Void>

    fun purchaseHourglassItem(purchaseType: String, key: String): Flowable<Void>

    fun purchaseQuest(key: String): Flowable<Void>

    fun purchaseItem(purchaseType: String, key: String): Flowable<Void>

    fun togglePinnedItem(item: ShopItem): Flowable<List<ShopItem>>
}
