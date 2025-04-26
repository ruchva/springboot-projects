package gob.ypfb.lumira.utils;

import org.modelmapper.ModelMapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MapperUtil {
    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        ModelMapper modelMapper = new ModelMapper();
        return source
            .stream()
            .map(element -> modelMapper.map(element, targetClass))
            .collect(Collectors.toList());
    }

    public static <R> R convertToType(Object source, Class<R> targetClass) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(source, targetClass);
    }

    public static HashMap<String, String> dtoToHashMap(Object dto) {
        // Creamos un HashMap
        HashMap<String, String> map = new HashMap<>();

        // Recorremos los atributos del DTO
        for (Field field : dto.getClass().getDeclaredFields()) {
            // Obtenemos el nombre del atributo
            String name = field.getName();

            // Obtenemos el valor del atributo
            try {
                field.setAccessible(true);
                Object value = field.get(dto);

                // Agregamos el atributo al HashMap
                map.put(name, value.toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return map;
    }
}
