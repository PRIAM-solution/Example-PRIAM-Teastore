/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tools.descartes.petstore.image.cache;

import java.util.function.Predicate;

import tools.descartes.petstore.image.cache.entry.ICachable;
import tools.descartes.petstore.image.cache.entry.TimedEntry;
import tools.descartes.petstore.image.cache.rules.CacheAll;
import tools.descartes.petstore.image.storage.IDataStorage;

public class MostRecentlyUsed<T extends ICachable<T>> extends AbstractTreeCache<T, TimedEntry<T>> {

	public MostRecentlyUsed() {
		this(IDataCache.STD_MAX_CACHE_SIZE);
	}
	
	public MostRecentlyUsed(long maxCacheSize) {
		this(maxCacheSize, new CacheAll<T>());
	}
	
	public MostRecentlyUsed(long maxCacheSize, Predicate<T> cachingRule) {
		this(null, maxCacheSize, cachingRule);
	}
	
	public MostRecentlyUsed(IDataStorage<T> cachedStorage, long maxCacheSize, Predicate<T> cachingRule) {
		super(cachedStorage, maxCacheSize, cachingRule, (a,b) -> a.getTime() - b.getTime() < 0 
				? 1 : (a.getTime() - b.getTime() > 0 ? -1 : (a.getId() < b.getId() 
						? -1 : (a.getId() == b.getId() ? 0 : 1))));
	}

	@Override
	protected TimedEntry<T> createEntry(T data) {
		return new TimedEntry<>(data);
	}

}
