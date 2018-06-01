package delin.frame.util;

import java.util.HashSet;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;



public class StreamUtil {
	public static<T> HashSet<T> find(HashSet<T> source,String key,Function<T,String> func){
		return (HashSet<T>) source.stream()
		.filter(e->func.apply(e).contains(key))
		.collect(Collectors.toCollection(HashSet<T>::new));
	}
	
	public static<T,S> T find(HashSet<T> source,S target,Function<T,S> func,Function<S,Boolean> filter) {
		Optional<T> res=source.stream()
				.filter(e->filter.apply(func.apply(e)))
				.reduce((acc,next)->acc=next);
			if (res.isPresent()) {
				return res.get();
			}
			return null;
	}
}
