package home.smartland.functional.portfolio

import arrow.core.raise.*
import java.math.BigDecimal


//interface CreatePortfolioUseCase {
//    fun Raise<DomainError>.createPortfolio(model: CreatePortfolio): PortfolioId
//}


interface Jobs {
    fun Raise<JobError>.getById(id: JobId): Job

    context (Logger, Raise<JobError>)
    fun findById(id: JobId): Job
}

class LiveJobs : Jobs {
    override fun Raise<JobError>.getById(id: JobId): Job =
        JOBS_DATABASE[id] ?: raise(JobNotFound(id))

    context(Logger, Raise<JobError>)
    override fun findById(id: JobId): Job {
//        info("Retrieving job with id $id")
        return JOBS_DATABASE[id] ?: raise(JobNotFound(id))
    }
}

interface JsonScope<T> {
    fun T.toJson(): String
}

interface Logger {
    fun info(message: String)
}

val consoleLogger = object : Logger {
    override fun info(message: String) {
        println("[INFO] $message")
    }
}

sealed interface JobError
data class JobNotFound(val jobId: JobId) : JobError
data class GenericError(val cause: String) : JobError
data object NegativeSalary : JobError

data class Job(val id: JobId, val company: Company, val role: Role, val salary: Salary)

@JvmInline
value class JobId(val value: Long)

@JvmInline
value class Company(val name: String)

@JvmInline
value class Role(val name: String)

@JvmInline
value class Salary(val value: Double) {
    operator fun compareTo(other: Salary): Int = value.compareTo(other.value)
}

val JOBS_DATABASE: Map<JobId, Job> = mapOf(
    JobId(1) to Job(
        JobId(1),
        Company("Apple, Inc."),
        Role("Software Engineer"),
        Salary(70_000.00),
    ),
    JobId(2) to Job(
        JobId(2),
        Company("Microsoft"),
        Role("Software Engineer"),
        Salary(80_000.00),
    ),
    JobId(3) to Job(
        JobId(3),
        Company("Google"),
        Role("Software Engineer"),
        Salary(90_000.00),
    ),
)

fun Raise<JobNotFound>.appleJob(): Job = JOBS_DATABASE[JobId(1)]!!
fun Raise<JobNotFound>.jobNotFound(): Job = raise(JobNotFound(JobId(42)))

class CurrencyConverter {
    @Throws(IllegalArgumentException::class)
    fun convertUsdToEur(amount: BigDecimal?): BigDecimal =
        if (amount == null || amount < BigDecimal.ZERO) {
            throw IllegalArgumentException("Amount must be positive")
        } else {
            amount * BigDecimal.valueOf(0.91)
        }
}


class JobsService(private val jobs: Jobs) {

    fun printSalary(jobId: JobId) = with (consoleLogger) {
        fold(
            block = { jobs.findById(jobId) },
            recover = { error: JobError ->
                when (error) {
                    is JobNotFound -> println("Job with id ${jobId.value} not found")
                    else -> println("An error was raised: $error")
                }
            },
            transform = { job: Job ->
                println("Job salary for job with id ${jobId.value} is ${job.salary}")
            },
//            catch = (Throwable::printStackTrace)
        )
    }

}



fun main() {
    val appleJobId = JobId(1)
    val jobs = LiveJobs()
    val jobService = JobsService(jobs)
    jobService.printSalary(appleJobId)
}