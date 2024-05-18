package ma.zs.carriere.zynerator.process;

import ma.zs.carriere.zynerator.audit.AuditBusinessObject;

public interface AbstractProcess<I extends AbstractProcessInput, K extends AbstractProcessOutput, T extends AuditBusinessObject> {
    Result<I, K, T> execute(I input);
}
