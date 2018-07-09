package com.example.computeserver.security.permission;

import com.example.computeserver.dao.PermissionDAO;
import com.example.computeserver.entity.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 处理数据库中用户权限
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyInvocationSecurityMetadataSourceService.class);
    @Autowired
    private PermissionDAO permissionDao;
    private HashMap<String, Collection<ConfigAttribute>> map;

    /**
     * 加载权限表中所有权限，在第一次请求时全部加载，以后不需要加载（缺点：新增权限时需要调用API刷新此权限集合）
     */
    public void loadResourceDefine() {
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        List<Permission> permissions = permissionDao.findAll();
        for (Permission permission : permissions) {
            array = new ArrayList<>();
            cfg = new SecurityConfig(permission.getName());
            //此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。
            //此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
            array.add(cfg);
            //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
            map.put(permission.getUrl(), array);
        }
    }

    /**
     * 通过权限表查找url对应的permission-name
     * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。
     * * 如果请求的URL未在权限表中定义，则放行，允许用户访问。
     * * 此处可优化，可直接查询数据库是否有此URL的权限，不需要全部查询出再过滤（更新权限时也不需要调用API刷新权限集合）
     *
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (map == null) loadResourceDefine();
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();

        String requestUrl = request.getServletPath();
        if (request.getPathInfo() != null) {
            requestUrl = requestUrl + request.getPathInfo();
        }
        LOGGER.info("***************** request url ：" + requestUrl + " ******************");
        String meathod = request.getMethod();
        String action = request.getParameter("action");
        String url = request.getRequestURI();

        AntPathRequestMatcher matcher;
        String resUrl;
        for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if (matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
