package org.example.proxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.example.cache.Cache;
import org.example.cache.impl.CacheLfu;
import org.example.cache.impl.CacheLru;
import org.example.exception.ConfigException;
import org.example.model.dto.CatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.example.constant.Constant.LFU;
import static org.example.constant.Constant.LRU;

/**
 * Прокси-сервис для кэширования вызовов методов.
 * Класс CacheProxyService является аспектом (Aspect) и предоставляет функциональность
 * для кэширования вызовов методов сервиса CatService.
 * Используется для улучшения производительности и сокращения времени выполнения запросов
 * путем кэширования результатов выполнения методов.
 *
 * @author Витикова Мария
 */
@Component
public class CacheProxyService {

    private final Cache<Long, CatDto> cache;

    /**
     * Создает объект CacheProxyService и инициализирует кэш в соответствии с конфигурацией.
     */
    @Autowired
    public CacheProxyService(@Value("${cache.algorithm}") String algorithm, @Value("${cache.capacity}") int capacity) {
        if (algorithm.equals(LRU)) {
            this.cache = new CacheLru<>(capacity);
        } else if (algorithm.equals(LFU)) {
            this.cache = new CacheLfu<>(capacity);
        } else {
            throw new ConfigException();
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    @Pointcut("execution(* org.example.service.impl.CatServiceImpl.getById(..)))")
    public void getMethod() {

    }

    @Pointcut("execution(* org.example.service.impl.CatServiceImpl.create(..)))")
    public void createMethod() {

    }

    @Pointcut("execution(* org.example.service.impl.CatServiceImpl.update(..)))")
    public void updateMethod() {

    }

    @Pointcut("execution(* org.example.service.impl.CatServiceImpl.delete(..)))")
    public void deleteMethod() {

    }

    /**
     * Возвращает объект CatDto по указанному идентификатору.
     * Если объект есть в кэше, метод возвращает его из кэша.
     * В противном случае, вызывает метод из оригинального сервиса и кэширует результат.
     *
     * @param pjp прокси-объект для вызова оригинального метода
     * @return объект CatDto
     * @throws Throwable при возникновении исключения при выполнении оригинального метода
     */
    @Around("getMethod()")
    public Object doGet(ProceedingJoinPoint pjp) throws Throwable {
        Long id = (Long) pjp.getArgs()[0];
        if (cache.get(id) == null) {
            CatDto catDto = (CatDto) pjp.proceed();
            cache.put(id, catDto);
            return catDto;
        } else {
            return cache.get(id);
        }
    }

    /**
     * Выполняет создание объекта с кэшированием результата.
     * После выполнения метода, результат добавляется в кэш.
     *
     * @param pjp прокси-объект для вызова оригинального метода
     * @return объект, созданный методом, с кэшированным результатом
     * @throws Throwable если возникла ошибка при выполнении оригинального метода
     */
    @Around("createMethod()")
    public Object doCreate(ProceedingJoinPoint pjp) throws Throwable {
        CatDto catDto = (CatDto) pjp.proceed();
        cache.put(catDto.getId(), catDto);
        return catDto;
    }

    /**
     * Выполняет обновление объекта с кэшированием результата.
     * После выполнения метода, результат обновления добавляется в кэш.
     *
     * @param pjp прокси-объект для вызова оригинального метода
     * @return объект, обновленный методом, с кэшированным результатом
     * @throws Throwable если возникла ошибка при выполнении оригинального метода
     */
    @Around("updateMethod()")
    public Object doUpdate(ProceedingJoinPoint pjp) throws Throwable {
        Long id = (Long) pjp.getArgs()[0];
        CatDto catDto = (CatDto) pjp.getArgs()[1];
        pjp.proceed();
        cache.put(id, catDto);
        return catDto;
    }

    /**
     * Выполняет удаление объекта с кэшированием результата.
     * После выполнения метода, соответствующий объект удаляется из кэша.
     *
     * @param pjp прокси-объект для вызова оригинального метода
     * @return идентификатор удаленного объекта
     * @throws Throwable если возникла ошибка при выполнении оригинального метода
     */
    @Around("deleteMethod()")
    public Object doDelete(ProceedingJoinPoint pjp) throws Throwable {
        Long id = (Long) pjp.getArgs()[0];
        pjp.proceed();
        cache.remove(id);
        return id;
    }
}
