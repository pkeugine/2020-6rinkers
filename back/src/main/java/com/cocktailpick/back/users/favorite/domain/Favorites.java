package com.cocktailpick.back.users.favorite.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import com.cocktailpick.back.cocktail.domain.Cocktail;
import com.cocktailpick.back.common.exceptions.ErrorCode;
import com.cocktailpick.back.common.exceptions.InvalidValueException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable
public class Favorites {
	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<Favorite> favorites = new ArrayList<>();

	public static Favorites empty() {
		return new Favorites();
	}

	public void addFavorite(Favorite favorite) {
		if (isContainingFavorite(favorite)) {
			throw new InvalidValueException(ErrorCode.FAVORITE_DUPLICATED);
		}

		favorites.add(favorite);
	}

	private boolean isContainingFavorite(Favorite inputFavorite) {
		return favorites.stream()
			.anyMatch(favorite -> favorite.isSameCocktailFavorite(inputFavorite));
	}

	public void deleteFavorite(Long cocktailId) {
		this.favorites = favorites.stream()
			.filter(favorite -> !favorite.getCocktail().getId().equals(cocktailId))
			.collect(Collectors.toList());
	}

	public boolean isContainCocktail(Cocktail cocktail) {
		return favorites.stream()
			.anyMatch(favorite -> favorite.isContainCocktail(cocktail));
	}
}
