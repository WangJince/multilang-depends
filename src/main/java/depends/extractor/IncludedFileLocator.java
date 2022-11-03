/*
MIT License

Copyright (c) 2018-2019 Gang ZHANG

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package depends.extractor;

import multilang.depends.util.file.FileUtil;

import java.io.File;
import java.util.List;

/**
 * Search file in all included path
 */
public final class IncludedFileLocator {
	private List<String> includesPath;
	public IncludedFileLocator(List<String> includedPath) {
		this.includesPath = includedPath;
	}

	/**
	 * Search file in all included path
	 *    * search the filename directly
	 *    * search the filename based in given start path (usually current working directory
	 *    * search the filename in all included paths
	 * @param dirPath
	 * @param importedFilename
	 * @return
	 */
	public String uniqFileName(String dirPath, String importedFilename) {
		if (FileUtil.existFile(importedFilename)) return FileUtil.uniqFilePath(importedFilename);
		if (dirPath!=null) {
			String path = dirPath + File.separator + importedFilename;
			if (FileUtil.existFile(path)) return FileUtil.uniqFilePath(path);
		}
		for (String includePath:includesPath) {
			String path = includePath + File.separator + importedFilename;
			if (FileUtil.existFile(path)) return FileUtil.uniqFilePath(path);
		}
		return null;
	}
}
