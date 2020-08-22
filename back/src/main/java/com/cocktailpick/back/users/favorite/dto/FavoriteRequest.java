package com.cocktailpick.back.users.favorite.dto;

import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FavoriteRequest {
	@NotBlank
	private Long cocktailId;
}
