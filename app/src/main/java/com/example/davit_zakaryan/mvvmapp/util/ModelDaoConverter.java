package com.example.davit_zakaryan.mvvmapp.util;

import com.example.davit_zakaryan.mvvmapp.data.model.Element;
import com.example.davit_zakaryan.mvvmapp.data.network.model.ItemModel;

/**
 * Created by Davit_Zakaryan on 12/20/2017.
 */
public class ModelDaoConverter {

	private ModelDaoConverter() {
	}

	public static Element convertItem(ItemModel itemModel) {
		Element element = new Element();
		element.level.set(itemModel.level);
		element.description.set(itemModel.longDesc);
		element.shortDesc.set(itemModel.shortDesc);
		element.name.set(itemModel.title);
		element.url.set(itemModel.url);
		element.id.set(itemModel.id);
		return element;
	}

	public static ItemModel convertElement(Element element) {
		ItemModel itemModel = new ItemModel();
		itemModel.level = element.level.get();
		itemModel.longDesc = element.description.get();
		itemModel.shortDesc = element.shortDesc.get();
		itemModel.title = element.name.get();
		itemModel.url = element.url.get();
		itemModel.id = element.id.get();
		return itemModel;
	}
}
