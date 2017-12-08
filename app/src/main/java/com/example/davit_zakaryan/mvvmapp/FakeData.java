package com.example.davit_zakaryan.mvvmapp;

import com.example.davit_zakaryan.mvvmapp.data.model.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Davit_Zakaryan on 12/6/2017.
 */

public class FakeData {


	private static Element INSTANCE;
	private static List<Element> ELEMENT_LIST;

	public static Element getElementInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Element();
			INSTANCE.url = "http://www.planwallpaper.com/static/images/desktop-year-of-the-tiger-images-wallpaper.jpg";
			INSTANCE.url = "https://i.neoseeker.com/ca/spellforce_2_dragon_storm_conceptart_RLzl6.jpg";
			INSTANCE.level.set(1);
			INSTANCE.name = "Simple name";
			INSTANCE.shortDesc = "Short description";
			INSTANCE.description = "";
		}
		return INSTANCE;
	}

	public static List<Element> getElementList() {
		if (ELEMENT_LIST == null) {
			ELEMENT_LIST = new ArrayList<>();
			Element element;
			Random rand = new Random();

			for (int i = 0; i < 100; i++) {
				int randomLevel = rand.nextInt(10) + 1;
				element = new Element();
				switch (i % 3) {
					case 0:
						element.url = "http://www.planwallpaper.com/static/images/desktop-year-of-the-tiger-images-wallpaper.jpg";
						element.name = "Tiger";
						element.shortDesc = "Short description";
						element.description = tigerDescription;
						break;
					case 1:
						element.url = "https://i.neoseeker.com/ca/spellforce_2_dragon_storm_conceptart_RLzl6.jpg";
						element.name = "Dragon";
						element.description = dragonDescription;
						break;
					case 2:
						element.url = "https://puxccbo05z-flywheel.netdna-ssl.com/wp-content/uploads/2015/02/black-mamba-1.jpg";
						element.name = "Black mamba";
						element.description = mambaDescription;
						break;
				}
				element.level.set(randomLevel);
				ELEMENT_LIST.add(element);
			}
		}
		return ELEMENT_LIST;
	}


	public static String mambaDescription = "Black mamba snakes (Dendroaspis polylepis) are also known as the Black-mouthed mamba, Southern brown mamba or Swart mamba and they are some of the most venomous snakes in the world. The Black Mamba snake is the largest venomous snake in Africa and the second largest venomous snake in the world, the only other snake larger is the King Cobra.";
	public static String dragonDescription = "A dragon is a legendary creature, typically scaled or fire-spewing and with serpentine, reptilian or avian traits, that features in the myths of many cultures around world.";
	public static String tigerDescription = "The white tiger or bleached tiger is a pigmentation variant of the Bengal tiger, which is reported in the wild from time to time in the Indian states of Assam, West Bengal and Bihar in the Sunderbans region and especially in the former State of Rewa";
}