package com.example.ankitrainingsystem.service;

import com.example.ankitrainingsystem.model.Dictionary;
import com.example.ankitrainingsystem.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;




@Service
public class DictService {
    @Autowired
    protected MutableAclService mutableAclService;

    final DictionaryRepository repository;

    @Autowired
    public DictService(DictionaryRepository repository) {
        this.repository = repository;
    }

    public void add(Dictionary dictionary) {
        repository.save( dictionary );
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Sid owner = new PrincipalSid( authentication );
        ObjectIdentity oid = new ObjectIdentityImpl( dictionary.getClass(), dictionary.getId() );

        final Sid admin = new GrantedAuthoritySid("ROLE_USER");

        MutableAcl acl = mutableAclService.createAcl( oid );
        acl.setOwner( owner );
        acl.insertAce( acl.getEntries().size(), BasePermission.ADMINISTRATION, admin, true );

        mutableAclService.updateAcl( acl );


    }
}
