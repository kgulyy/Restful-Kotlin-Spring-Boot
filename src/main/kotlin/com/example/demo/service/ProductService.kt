package com.example.demo.service

import com.example.demo.system.Product
import com.example.demo.system.ProductRepository
import org.springframework.stereotype.Service

@Service // Позволяем IoC контейнеру внедрять класс
class ProductService(private val productRepository: ProductRepository) { // Внедряем репозиторий в качестве зависимости
    // Возвращаем коллекцию сущностей, функциональная запись с указанием типа
    fun all(): Iterable<Product> = productRepository.findAll()

    fun get(id: Long): Product = productRepository.findOne(id)

    fun add(product: Product) = productRepository.save(product)

    // Сохраняем копию объекта с указанным id в БД.
    fun edit(id: Long, product: Product) = productRepository.save(product.copy(id = id))
    // Идиоматика Kotlin говорит что НЕ изменяемый - всегда лучше чем изменяемый (никто не поправит значение в другом потоке)
    // и предлагает метод copy для копирования объектов (специальных классов для хранения данных) с возможностью замены значений

    fun remove(id: Long) = productRepository.delete(id)
}
