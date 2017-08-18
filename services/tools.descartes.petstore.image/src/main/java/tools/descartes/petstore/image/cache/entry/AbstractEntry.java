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
package tools.descartes.petstore.image.cache.entry;

public abstract class AbstractEntry<D extends ICachable<D>> {
	
	private D data;
	
	public AbstractEntry(D data) {
		if (data == null)
			throw new NullPointerException("Supplied data is null.");
		
		this.data = data;
	}

	public D getData() {
		return data;
	}
	
	public abstract void wasUsed();
	
	public long getId() {
		return data.getId();
	}
	
	public long getByteSize() {
		return data.getByteSize();
	}
	
}
