import React from "react";

const CocktailItem = ({
  cocktail: tail,
  updateFromSelectedCocktail,
  onDeleteCocktail,
}) => {
  return (
    <div
      className="cocktailItem"
      data-cocktail-id={tail.id}
      onClick={updateFromSelectedCocktail}
    >
      <img src={tail.imageUrl} alt={tail.name} />
      {tail.name}
      <div className="delete" onClick={(e) => onDeleteCocktail(tail.id, e)}>
        삭제
      </div>
    </div>
  );
};

export default CocktailItem;
