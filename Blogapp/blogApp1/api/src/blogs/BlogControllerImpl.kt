package blogs

import blogs.core.services.BlogService
import utility.Response
import utility.ResponseType
import javax.inject.Singleton

@Singleton
class BlogControllerImpl(
    private val blogService: BlogService
) : BlogController {
    override fun getAll(): Response<Any> {
        val response = blogService.getAll()
        if (response.isEmpty()){
            return Response(ResponseType.BAD_REQUEST, body = "blog not found")
        }
        return Response(ResponseType.SUCCESS, body = response)
    }

    override fun createUser(userName: String): Response<Any> {
        val response = blogService.createUser(userName)
        return Response(ResponseType.SUCCESS, body = response)
    }

    override fun createUserBlog(userId: Long, title: String, content: String): Response<Any> {
        val response = blogService.createUserBlog(userId, title, content)
        if (response.isNotEmpty()){
            return Response(ResponseType.SUCCESS, body = response)
        }else {
            return Response(ResponseType.NO_CONTENT, body = "user not found")
        }
    }

    override fun updateUserBlog(userId: Long, blogId: Long, title: String, content: String): Response<Any> {
        val response= blogService.updateUserBlog(userId,blogId,title,content)
        if(response.isEmpty()){
            return Response(ResponseType.NOT_FOUND, body = "no content found")

        }
        return Response(ResponseType.SUCCESS, body = "blog updated successfully")
    }

    override fun deleteUserBlog(userId: Long, blogId: Long): Response<Any> {
        val response=blogService.deleteUserBlog(userId,blogId)
        if(response.isEmpty()){
            return Response(ResponseType.SUCCESS, body = "blog deleted successfully")
        }else {
            return Response(ResponseType.NOT_FOUND, body = "no content found")
        }
    }

    override fun displayUserBlog(userId: Long): Response<Any> {
        val response = blogService.displayUserBlog(userId)
        if (response.isEmpty()){
            return Response(ResponseType.NOT_FOUND, body = "user not found")
        }else{
            return Response(ResponseType.SUCCESS, body = response)
        }


    }

    override fun displayAllUser(): Response<Any> {
        val response = blogService.displayAllUser()
        if (response.isEmpty()) {
            return Response(ResponseType.SUCCESS, body = response)
        }
        return Response(ResponseType.NOT_FOUND, body = "not found")
    }



}

