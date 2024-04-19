package ra.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ra.model.Catalog;

public class CatalogService implements IGenericService<Catalog, Integer> {
    private List<Catalog> catalogs = new ArrayList<>(); // Initialize the list



    @Override
    public List<Catalog> getAll() {
        return catalogs;
    }

    @Override
    public void save(Catalog catalog) {
        catalogs.add(catalog);
    }

    @Override
    public Catalog findById(Integer id) {
        for (Catalog catalog : catalogs) {
            if (catalog.getCatalogId() == id) {
                return catalog;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        Catalog catalog = findById(id);
        if (catalog != null && catalog.getProducts().isEmpty()) {
            catalogs.remove(catalog);
        }
    }

    public Catalog getCatalogByName(String name) {
        for (Catalog catalog : catalogs) {
            if (catalog.getCatalogName().equals(name)) {
                return catalog;
            }
        }
        return null;
    }

    public void addCatalogs(int numCatalogs) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < numCatalogs; i++) {
            Catalog catalog = new Catalog();
            System.out.print("Mời bạn nhập mã danh mục (Chỉ được nhập số): ");
            catalog.setCatalogId(scanner.nextInt());
            scanner.nextLine();

            String catalogName;
            do {
                System.out.print("Mời bạn thêm tên danh mục (Không được để trống): ");
                catalogName = scanner.nextLine();
            } while (catalogName.isEmpty());

            catalog.setCatalogName(catalogName);

            String descriptions;
            do {
                System.out.print("Mời bạn mô tả danh mục (không được để trống): ");
                descriptions = scanner.nextLine();
            } while (descriptions.isEmpty());

            catalog.setDescriptions(descriptions);

            save(catalog);
        }
    }

    public void displayAllCatalogs() {
        for (Catalog catalog : getAll()) {
            System.out.println(catalog);
        }
    }

    public void editCatalogName(int id, String newName) {
        Catalog catalog = findById(id);
        if (catalog != null) {
            catalog.setCatalogName(newName);
            save(catalog);
        }
    }

    public void deleteCatalog(int id) {
        delete(id);
    }
}