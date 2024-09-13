package com.smartpoke.api.feature.product.specification;

import com.smartpoke.api.feature.product.model.Product;
import com.smartpoke.api.feature.recipe.model.Recipe;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> nameLike(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isEmpty()) {
                return cb.conjunction(); // Sin filtro para nombre
            } else {
                return cb.like(root.get("name"), "%" + name + "%");
            }
        };
    }

    public static Specification<Product> nameOrDescriptionLike(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.isEmpty()) {
                return cb.conjunction(); // Sin filtro si la palabra clave está vacía
            } else {
                String pattern = "%" + keyword.toLowerCase() + "%";
                return cb.or(
                        cb.like(cb.lower(root.get("name")), pattern),
                        cb.like(cb.lower(root.get("description")), pattern),
                        cb.like(cb.lower(root.get("keywords")), pattern) // Asumiendo que existe un campo 'keywords'
                );
            }
        };
    }

    public static Specification<Product> eanEqual(String ean) {
        return (root, query, cb) -> {
            if (ean == null || ean.isEmpty()) {
                return cb.conjunction(); // Sin filtro para EAN
            } else {
                return cb.equal(root.get("ean"), ean);
            }
        };
    }

    public static Specification<Product> brandLike(String brand) {
        return (root, query, cb) -> {
            if (brand == null || brand.isEmpty()) {
                return cb.conjunction(); // Sin filtro para marca
            } else {
                return cb.like(root.get("brand"), "%" + brand + "%");
            }
        };
    }

    public static Specification<Product> categoryLike(String category) {
        return (root, query, cb) -> {
            if (category == null || category.isEmpty()) {
                return cb.conjunction(); // Sin filtro para categoría
            } else {
                return cb.like(root.get("category"), "%" + category + "%");
            }
        };
    }

    public static Specification<Product> keywordsLike(String keywords) {
        return (root, query, cb) -> {
            if (keywords == null || keywords.isEmpty()) {
                return cb.conjunction(); // Sin filtro para palabras clave
            } else {
                return cb.like(root.get("keywords"), "%" + keywords + "%");
            }
        };
    }

    public static Specification<Product> tagsIn(List<String> tags) {
        return (root, query, cb) -> {
            if (tags == null || tags.isEmpty()) {
                return cb.conjunction(); // Sin filtro para etiquetas
            } else {
                return root.join("tags", JoinType.LEFT).get("name").in(tags);
            }
        };
    }

    public static Specification<Product> allergensIn(List<String> allergens) {
        return (root, query, cb) -> {
            if (allergens == null || allergens.isEmpty()) {
                return cb.conjunction(); // Sin filtro para alérgenos
            } else {
                return root.join("allergens", JoinType.LEFT).get("name").in(allergens);
            }
        };
    }

    public static Specification<Product> excludeBrandRecipe() {
        return (root, query, cb) -> cb.notEqual(cb.upper(root.get("brand")), "RECIPE");
    }
}
