package io.github.haodongling.ppjokenew.ui.home

import com.kunminx.architecture.ui.callback.UnPeekLiveData
import io.github.haodongling.lib.common.core.BaseViewModel
import io.github.haodongling.lib.common.model.UiModel
import io.github.haodongling.lib.common.network.doError
import io.github.haodongling.lib.common.network.doSuccess
import io.github.haodongling.ppjokenew.api.RetrofitClient
import io.github.haodongling.ppjokenew.model.Feed
import io.github.haodongling.ppjokenew.utils.CacheUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * Author: tangyuan
 * Time : 2021/11/24
 * Description:
 */
class HomeViewModel : BaseViewModel() {
    var mFeedType: String = ""
    val feedListState = UnPeekLiveData<UiModel<ArrayList<Feed>>>()
    fun getData(key: Int, count: Int, isRefresh: Boolean) {
        launchOnUI {
            flow<UiModel<ArrayList<Feed>>> {
                RetrofitClient.FeedService.getHotFeedList(mFeedType, CacheUtil.getUserId(), key, count).doSuccess {
                    emit(UiModel(showSuccess = it, showLoading = false, isRefresh = isRefresh))
                }.doError {
                    emit(UiModel(showLoading = false, showError = it.errMessage))
                }
            }.flowOn(Dispatchers.IO).onStart {
                emit(UiModel(showLoading = true))
            }.catch {
                emit(UiModel(showError = it.message, showLoading = false, showEnd = false))
            }.collect {
                feedListState.value = it
            }

        }
    }
}