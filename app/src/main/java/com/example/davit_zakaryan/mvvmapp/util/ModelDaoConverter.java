package com.example.davit_zakaryan.mvvmapp.util;

import com.example.davit_zakaryan.mvvmapp.data.db.model.ElementEntity;
import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.data.network.model.ItemModel;

/**
 * Created by Davit_Zakaryan on 12/20/2017.
 */
public class ModelDaoConverter {

	private ModelDaoConverter() {
	}

	public static Element convertToElement(ItemModel itemModel) {
		Element element = new Element();
		element.serverId.set(itemModel.id);
		element.level.set(itemModel.level);
		element.description.set(itemModel.longDesc);
		element.shortDesc.set(itemModel.shortDesc);
		element.name.set(itemModel.title);
		element.url.set(itemModel.url);
		return element;
	}

	public static ItemModel convertToItemModel(Element element) {
		ItemModel itemModel = new ItemModel();
		itemModel.level = element.level.get();
		itemModel.longDesc = element.description.get();
		itemModel.shortDesc = element.shortDesc.get();
		itemModel.title = element.name.get();
		itemModel.url = element.url.get();
		itemModel.id = element.id.get();
		return itemModel;
	}


	public static ElementEntity convertToElementEntity(Element element) {
		ElementEntity elementEntity = new ElementEntity();
		elementEntity.setLevel(element.level.get());
		elementEntity.setDescription(element.description.get());
		elementEntity.setShortDesc(element.shortDesc.get());
		elementEntity.setTitle(element.name.get());
		elementEntity.setUrl(element.url.get());
		elementEntity.setServerId(element.id.get());
		return elementEntity;
	}

	public static Element convertToElement(ElementEntity elementEntity) {
		Element element = new Element();
		element.level.set(elementEntity.getLevel());
		element.description.set(elementEntity.getDescription());
		element.shortDesc.set(elementEntity.getShortDesc());
		element.name.set(elementEntity.getTitle());
		element.url.set(elementEntity.getUrl());
		element.id.set(elementEntity.getServerId());
		return element;
	}

}
